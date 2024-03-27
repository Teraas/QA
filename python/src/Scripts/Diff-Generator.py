import pandas
import numpy
import os
import datetime
import argparse

columns = ['QueryName','Category','Result','Environment','QueryTime(ms)','TC StartTime(Epoch ms)','TC EndTime(Epoch ms)','SystemTime(GMT)','PRNumber','BuildJobName','ServiceName','DevName','TestMode','TCPriority','isChaos','DependencyName','ToxicType','ToxicParameter','testCode', 'T','U','V','W','X','Y','Z','AA','AB','AC','AD','AE']
dfFail_A = pandas.DataFrame(columns=columns)
dfFail_B = pandas.DataFrame(columns=columns)
dfFail_Both = pandas.DataFrame(columns=columns)
dfFail_ANotB = pandas.DataFrame(columns=columns)
dfFail_BNotA = pandas.DataFrame(columns=columns)
indexSlack = ['Pre BLT Slack Link', 'Post BLT Slack Link']
slackPrev = None
slackCurrent = None
def readCSVs():
    print(path1)
    print(path2)
    pandas.set_option('display.max_columns', None)
    pandas.set_option('display.width', None)
    pandas.set_option('display.max_colwidth', None)
    df1 = pandas.read_csv(path1, names=columns)
    df2 = pandas.read_csv(path2, sep='delimiter', header=None)
    #df1 = df1.iloc[1: , :]
    #df2 = df2.iloc[1: , :]
    #df1.columns = columns
    #print(df1.iloc[0].to_string().split(','))
    #lst = list(df1)
    #tests = df1.iloc[:,1]
    lst = df1['QueryName'].tolist()
    #print(df1.filter(like='QueryName').head())
    tup = tuple(lst)
    myset = set(lst)
    print(len(lst))
    print(len(tup))
    print(len(myset))
    df1['ColumnA'] = df1[df1.columns[0:1]].apply(
        lambda x: ','.join(x.astype(str)),
        axis=1
    )
    df1['Identifier'] = df1['QueryName'] + df1['Category'] + df1['testCode']
    df1_1 = df1.sort_values(by='QueryName',ascending=True)
    lst1 = df1_1['Identifier'].tolist()
    print(len(set(lst1)))


def comparatorSingle(dfX, lengthx, type):
    print(" tests in filePrev -: " + str(lengthx))
    dfA_1 = dfX.sort_values(by='QueryName',ascending=True)

    for i in range(1,lengthx):
        #print(dfA_1.iloc[[i]]['Result'].to_string())
        result = dfA_1.iloc[[i]]['Result'].to_string().split()[1]
        dfRowA_list = dfA_1.iloc[[i]].to_numpy().tolist() # convert the row  to list
        #print(resultA)
        if str(result) == 'Fail' and type == 'A':
            addToDataFrame(dfFail_A, dfRowA_list[0])
            addToDataFrame(dfFail_ANotB, dfRowA_list[0])
        if str(result) == 'Fail' and type == 'B':
            addToDataFrame(dfFail_B, dfRowA_list[0])
            addToDataFrame(dfFail_BNotA, dfRowA_list[0])

    print("------------ comparison complete ---------")
    print("Failures in prev ---------------------")
    print(dfFail_A)
    print(dfFail_B)
    print(dfFail_Both)
    # create a excel writer object
    ct = datetime.datetime.now()
    print("current time:-", ct)
    print ("current dir:- ",os.path.dirname(os.path.abspath(__file__) + "/.."))
    outDir = os.path.dirname(os.path.abspath(__file__))
    name = "API"
    if path1.__contains__("GQL"):
        name = "GQL"
    if path1.__contains__("GRPC"):
        name = "GRPC"
    with pandas.ExcelWriter(outDir + "/out/" + name + "-CSV-Diff-" + str(ct) + ".xlsx") as writer:

        print("creating analysis excel sheet ---------------------")
        dfFail_A.to_excel(writer, sheet_name="FailuresInPrevRun", index=False)
        dfFail_B.to_excel(writer, sheet_name="FailuresInCurrentRun", index=False)
        dfFail_Both.to_excel(writer, sheet_name="FailuresInBoth", index=False)
        dfFail_ANotB.to_excel(writer, sheet_name="FailuresOnlyInPrevRun", index=False)
        dfFail_BNotA.to_excel(writer, sheet_name="FailuresOnlyInCurrentRun", index=False)
        if slackPrev is not None:
            data = [[slackPrev], [slackCurrent]]
            slackDF = pandas.DataFrame(data, columns=['SlackLinks'], index=indexSlack)
            slackDF.to_excel(writer, sheet_name="SlackLinks", index=True)


def comparator(dfA,dfB, lengthA, lengthB):
    print(" tests in filePrev -: " + str(lengthA))
    print(" tests in fileCurrent -: " + str(lengthB))
    dfA_1 = dfA.sort_values(by='QueryName',ascending=True)
    dfB_1 = dfB.sort_values(by='QueryName',ascending=True)

    for i in range(1,lengthA):
        #print(dfA_1.iloc[[i]]['Result'].to_string())
        resultA = dfA_1.iloc[[i]]['Result'].to_string().split()[1]
        resultB = dfB_1.iloc[[i]]['Result'].to_string().split()[1]
        dfRowA_list = dfA_1.iloc[[i]].to_numpy().tolist() # convert the row  to list
        dfRowB_list = (dfB_1.iloc[[i]]).to_numpy().tolist() # convert the row  to list
        #print(resultA)
        if str(resultA) == 'Fail':
            addToDataFrame(dfFail_A, dfRowA_list[0])
        if str(resultB) == 'Fail':
            addToDataFrame(dfFail_B, dfRowB_list[0])
        if str(resultB) == 'Fail' and str(resultA) == 'Fail':
            addToDataFrame(dfFail_Both, dfRowA_list[0])
        if str(resultA) == 'Fail' and str(resultB) != 'Fail':
            addToDataFrame(dfFail_ANotB, dfRowA_list[0])
        if str(resultB) == 'Fail' and str(resultA) != 'Fail':
            addToDataFrame(dfFail_BNotA, dfRowB_list[0])

    print("------------ comparison complete ---------")
    print("Failures in prev ---------------------")
    print(dfFail_A)
    print(dfFail_B)
    print(dfFail_Both)
    # create a excel writer object
    ct = datetime.datetime.now()
    print("current time:-", ct)
    print ("current dir:- ",os.path.dirname(os.path.abspath(__file__) + "/.."))
    outDir = os.path.dirname(os.path.abspath(__file__))
    name = "API"
    if path1.__contains__("GQL"):
        name = "GQL"
    if path1.__contains__("GRPC"):
        name = "GRPC"
    with pandas.ExcelWriter(outDir + "/out/" + name + "-CSV-Diff-" + str(ct) + ".xlsx") as writer:

        print("creating analysis excel sheet ---------------------")
        dfFail_A.to_excel(writer, sheet_name="FailuresInPrevRun", index=False)
        dfFail_B.to_excel(writer, sheet_name="FailuresInCurrentRun", index=False)
        dfFail_Both.to_excel(writer, sheet_name="FailuresInBoth", index=False)
        dfFail_ANotB.to_excel(writer, sheet_name="FailuresOnlyInPrevRun", index=False)
        dfFail_BNotA.to_excel(writer, sheet_name="FailuresOnlyInCurrentRun", index=False)
        if slackPrev is not None:
            data = [[slackPrev], [slackCurrent]]
            slackDF = pandas.DataFrame(data, columns=['SlackLinks'], index=indexSlack)
            slackDF.to_excel(writer, sheet_name="SlackLinks", index=True)


def addToDataFrame(dataframe, dataframeToAdd):
    dataframe.loc[len(dataframe.index)] = dataframeToAdd
    #print(dataframe)


def compareCSVs( pathA, pathB):
    print(pathA)
    print(pathB)
    if pathA == 'NA##NA':
        print("previous run had no failures")
        dfB = pandas.read_csv(pathB, sep=',', names=columns)
        comparatorSingle(dfB, len(dfB), "B")
    elif pathB == 'NA##NA':
        print("current run had no failures")
        dfA = pandas.read_csv(pathA, sep=',',names=columns)
        comparatorSingle(dfA, len(dfA), "A")
    else:
        dfA = pandas.read_csv(pathA, sep=',',names=columns)
        dfB = pandas.read_csv(pathB, sep=',', names=columns)
        lengthA = len(dfA)
        lengthB = len(dfB)
        print(lengthA)
        print(lengthB)
        if lengthA != lengthB:
            print("Test count is diferent")
        else:
            comparator(dfA,dfB, lengthA, lengthB)

if __name__ == '__main__':
    path1 = '/Users/harish.kumar-mbp/Downloads/TestExecutionReport4.csv'
    path2 = '/Users/harish.kumar-mbp/Downloads/TestExecutionReport5.csv'
    parser = argparse.ArgumentParser()
    parser.add_argument("-m", "--mode", help="choose run modes - row_by_row or test_id", required=True)
    parser.add_argument("-p", "--filePrev", help="this is csv file path for previous run", required=True)
    parser.add_argument("-c", "--fileCurrent", help="this is csv file path for latest run ", required=True)
    parser.add_argument("--prevSlack", help="The slack link of prev run ", required=False)
    parser.add_argument("--currentSlack", help="The slack link of current run ", required=False)
    args = parser.parse_args()
    print(args)
    path1 = args.filePrev
    path2 = args.fileCurrent
    slackPrev = args.prevSlack
    slackCurrent = args.currentSlack
    mode = str(args.mode)
    if mode == 'row_by_row':
        compareCSVs(path1, path2)
    elif mode == 'test_id':
        print ('test_id mode is not supported in this version')
    else:
        print (mode + ' mode is not supported ')