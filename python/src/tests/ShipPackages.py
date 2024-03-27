import os


def getMaxWeight(arr):
    max = arr[0]
    for x in arr:
        if x>max:
            max = x
    return max


def getSum(arr):
    sum = 0
    for x in arr:
        sum = sum +x
    return sum


def returnMinWeight(arr, days):
    l = len(arr)
    max = getMaxWeight(arr)
    sum = getSum(arr)

    while max < sum:
        tmpdays = 0
        curr = 0
        for i in range(l):
            curr = curr + arr[i]
            if curr == max:
                tmpdays +=1
                curr = 0
            if curr > max:
                tmpdays +=1
                curr = arr[i]
        if curr >0:
            tmpdays +=1
        if tmpdays > days:
            max = max+1
        if tmpdays == days:
            print("result max", max)
            return max

if __name__ == '__main__':
    arr = [1,2,3,4,5,6,7,8,9,10]
    a = [[None for x in range(3)] for i in range(2)]
    print(a)
    cwd = os.getcwd()
    print(os.listdir(cwd))
    days = 5
    returnMinWeight(arr, days)