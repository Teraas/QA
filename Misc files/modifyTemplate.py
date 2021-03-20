import re
import os
import json
import pandas
import operator
import requests
import argparse
import datetime
from sys import platform
from datetime import timedelta
from requests_kerberos import HTTPKerberosAuth, OPTIONAL

# Modifies the values of placeholder in excel.
# While running the script from windows, please make sure to install XlsxWriter using 'pip install --user XlsxWriter'

placeholder_regex = {"DATE": "(<[D|d][A|a][T|t][E|e][+|-]\d*>)",
                     "SPN": "(<[S|s][P|p][N|n]\|.+\|.+>)"}
global_placeholder_map = dict()
date_str = str()
client_name = str()


def resolve_date_placeholders(placeholder_list):
    for placeholder in placeholder_list:
        operations = {"+": operator.add, "-": operator.sub}
        effective_date = str(operations[placeholder[5:6]](datetime.datetime.strptime(date_str, '%Y%m%d').now(),
                                                          timedelta(days=int(placeholder[6:-1]))))
        global_placeholder_map[placeholder] = str(effective_date)[:10]


def resolve_spn_placeholders(placeholder_list):
    for placeholder in placeholder_list:
        search_url = "http://moss." + client_name + ".c.ia55.net/service/securityService/searchSpns?filter=" + \
                     placeholder[placeholder.index("|") + 1:placeholder.rfind("|")] + ".in(" + \
                     placeholder[placeholder.rfind("|") + 1:-1] + ")&format=JSON"
        kerberos_auth = HTTPKerberosAuth(mutual_authentication=OPTIONAL)
        r = requests.get(search_url, auth=kerberos_auth)
        if "Exception" in r.content or r.status_code != 200 or r.content.strip() == "[ ]":  # In case spn isn't found
            global_placeholder_map[placeholder] = "null"
            continue
        global_placeholder_map[placeholder] = str(json.loads(r.content)[0])


def get_custom_placeholders(df, placeholder_key):
    placeholder_list = list()
    for index, row in df.iterrows():
        placeholder_list = placeholder_list + re.findall(placeholder_regex.get(placeholder_key),
                                                         "#".join([str(item) for item in list(row)]))
    if not placeholder_list:
        print("No placeholders to resolve.")
        return
    print("Placeholders to be resolved: " + str(set(placeholder_list)))
    if placeholder_key == "DATE":
        resolve_date_placeholders(set(placeholder_list))
    elif placeholder_key == "SPN":
        resolve_spn_placeholders(set(placeholder_list))
    print("Global placeholders: " + str(global_placeholder_map))


if __name__ == "__main__":
    # Reading arguments
    parser = argparse.ArgumentParser()
    parser.add_argument('--date', help='date for which data has been created')
    parser.add_argument('--uniqueKey', help='uniqueKey of reference data used')
    parser.add_argument('--excelLocation', help='path of the data file (can be passed multiple)')
    parser.add_argument('--clientname', help='Pod on which automation is being run')
    parser.set_defaults(feature=True)
    args = parser.parse_args()
    date_str = str(args.date)
    client_name = str(args.clientname)
    uniqueKey = str(args.uniqueKey)
    excel_locations = str(args.excelLocation)
    excel_location_array = excel_locations.split(',')
    for excel_location in excel_location_array:
        backup_location = excel_location.split('.xlsx')[0] + '_backup.xlsx'
        os.system(('copy' if 'win' in platform else 'scp') + ' ' + excel_location + ' ' + backup_location)
        excel_file = pandas.ExcelFile(excel_location)
        sheet_names = excel_file.sheet_names
        dict_of_sheet_df = dict()
        for sheet_name in sheet_names:
            df = pandas.read_excel(excel_location, sheet_name=sheet_name)
            df = df.replace('<uniqueKey>', uniqueKey, regex=True)
            df = df.replace('<DATE>', datetime.datetime.strptime(date_str, '%Y%m%d').date(), regex=True)
            for key in placeholder_regex:
                get_custom_placeholders(df, key)
            for key, value in global_placeholder_map.iteritems():
                df = df.replace(key, value)
            dict_of_sheet_df.update({sheet_name: df})
        writer = pandas.ExcelWriter(excel_location, engine='xlsxwriter')
        for sheet_name in dict_of_sheet_df:
            df = dict_of_sheet_df[sheet_name]
            df.to_excel(writer, sheet_name, index=False)
        writer.save()
    print("Successfully modified the excel file")
