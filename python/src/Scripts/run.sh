echo "run grpc"
#python Diff-Generator.py -m row_by_row -p /Users/harish.kumar-mbp/Downloads/BLT/GRPC1.csv -c /Users/harish.kumar-mbp/Downloads/BLT/GRPC2.csv --prevSlack https://tokopedia.slack.com/archives/C04376R630V/p1701961680157989 --currentSlack https://tokopedia.slack.com/archives/C04376R630V/p1667417218489939

echo "run gql 1"
python Diff-Generator.py -m row_by_row -p NA##NA -c /Users/harish.kumar-mbp/Downloads/BLT/GQL2a.csv --prevSlack https://tokopedia.slack.com/archives/C042VJASL6T/p1701961804264539 --currentSlack https://tokopedia.slack.com/archives/C042VJASL6T/p1701977139432949

echo "run gql 2"
#python Diff-Generator.py -m row_by_row -p /Users/harish.kumar-mbp/Downloads/BLT/GQL1b.csv -c /Users/harish.kumar-mbp/Downloads/BLT/GQL2b.csv --prevSlack https://tokopedia.slack.com/archives/C042VJASL6T/p1701961649611089 --currentSlack https://tokopedia.slack.com/archives/C042VJASL6T/p1701977052716189


echo "run api 1 2 3"
#python Diff-Generator.py -m row_by_row -p NA##NA -c /Users/harish.kumar-mbp/Downloads/BLT/API2a.csv --prevSlack https://tokopedia.slack.com/archives/C043NQANH0R/p1701961871464949 --currentSlack https://tokopedia.slack.com/archives/C043NQANH0R/p1665598252818609

#python Diff-Generator.py -m row_by_row -p /Users/harish.kumar-mbp/Downloads/BLT/API1b.csv -c /Users/harish.kumar-mbp/Downloads/BLT/API2b.csv --prevSlack https://tokopedia.slack.com/archives/C043NQANH0R/p1701961620913509 --currentSlack https://tokopedia.slack.com/archives/C043NQANH0R/p1665598379922579

#python Diff-Generator.py -m row_by_row -p /Users/harish.kumar-mbp/Downloads/BLT/API1c.csv -c /Users/harish.kumar-mbp/Downloads/BLT/API2c.csv --prevSlack https://tokopedia.slack.com/archives/C043NQANH0R/p1701961693856749 --currentSlack https://tokopedia.slack.com/archives/C043NQANH0R/p1665559174162109

