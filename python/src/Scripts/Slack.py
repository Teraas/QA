import os
import requests
from slack_sdk import WebClient

from slack_sdk.errors import SlackApiError
#SLACK_API_TOKEN='' # Your token here
def fetchHeadCount():

    CHANNEL_NAME='all-goto' # Your channel here

    channel_list = requests.get('https://slack.com/api/conversations.list?token=%s&types=%s' % (SLACK_API_TOKEN, 'public_channel,private_channel,im,mpim')).json()['channels']

    print("channel list -> " + str(len(channel_list)))
    # for i in channel_list:
    #     print(i['name'])
    for c in channel_list:
        if 'name' in c and c['name'] == CHANNEL_NAME:
            channel = c

    # Tokopedia-India, Test-engineers, all-goto
    channel_list1 = ['GC1EJFUNP','G466VN8GJ','C03P3BC3PGU']
    channel_listStr = ['#India-office','#test-engineers','#all-goto']
    #print("Headcount for Tokopedia India")
    #GetMembersCountForChannel(channel_list1[0])
    #print("Headcount for Test-engineers")
    #GetMembersCountForChannel(channel_list1[1])
    print("Headcount for all-goto")
    GetMembersCountForChannel(channel_list1[2], channel_listStr[2])
# for user in users_list:
#     if "email" in user['profile'] and user['id'] in members:
#         print(user['profile']['email'])

def GetMembersCountForChannel(channel, channelStr):
    #print(channel)
    resp_ = requests.get('https://slack.com/api/conversations.members?token=%s&channel=%s&limit=10000' % (SLACK_API_TOKEN, str(channel)))
    #print((resp_))
    members = resp_.json()['members']
    cursor = resp_.json()['response_metadata']['next_cursor']
    #print((cursor))
    memberCount = len(members)
    membersCount_ = len(members)
    while(membersCount_ ==1000):
        resp_ = requests.get('https://slack.com/api/conversations.members?token=%s&channel=%s&limit=10000&cursor=%s' % (SLACK_API_TOKEN, channel,cursor))
        #print((resp_))
        cursor = resp_.json()['response_metadata']['next_cursor']
        #print((cursor))

        members_ = resp_.json()['members']
        membersCount_=len(members_)
        memberCount +=membersCount_
        members.append(members_)
        #print(len(members_))
    users_list = requests.get('https://slack.com/api/users.list?token=%s' % SLACK_API_TOKEN).json()['members']
    print(memberCount)
    client = WebClient(token=SLACK_API_TOKEN)

    #client.chat_postMessage(channel='testharish', text="Today's Headcount for " + channelStr + " is -> " + str(memberCount))
if __name__ == '__main__':
    fetchHeadCount()