import multiprocessing
import threading
import time

result = []
# comunicating/sharing b/w processes is file File, shared mem, message pipes
# processes have their own memory. But threads share memory. threads are light weight. many inside a process
def square(arr):
    global result
    for x in arr:
        #time.sleep(1)
        print(x *x)
        result.append(x * x)
    print(" inside process res ", result)

def square1(arr, res):

    for idx, x in enumerate(arr):
        #time.sleep(1)
        print(x *x)
        res[idx] = (x * x)
    print(" inside process res ", res[:])
def cube(arr):
    for x in arr:
        time.sleep(1)
        print(x * x * x)

def squareWithQueue(arr, res):

    for idx, x in enumerate(arr):
        #time.sleep(1)
        print(x *x)
        res[idx] = (x * x)
    print(" inside process res ", res[:])

def lockWithMultiProcess():
    balance = multiprocessing.Value('i', 200)
    lock = multiprocessing.Lock()

    p1 = multiprocessing.Process(target=deduct, args=(balance,lock,))
    p2 = multiprocessing.Process(target=deposit, args=(balance,lock,))
    p1.start()
    p2.start()
    p1.join()
    p2.join()
    print("balance ", balance.value)

def deduct(balance,lock):
    for i in range(100):
        lock.acquire()
        balance.value = balance.value - 1
        lock.release()

def deposit(balance,lock):
    for i in range(100):
        lock.acquire()
        balance.value = balance.value + 1
        lock.release()

if __name__ == '__main__':
    # test
    arr = [1,2,3,4,5,6,7,8,9,10]
    res = multiprocessing.Array('i',10)
    # just like array we can use Queue. multiprocessing.Queue
    t = time.time()
    p1 = multiprocessing.Process(target=square1, args=(arr,res,))
    #p2 = multiprocessing.Process(target=cube, args=(arr,))

    p1.start()
    #p2.start()
    p1.join()
    #p2.join()
    lockWithMultiProcess()
    print("time taken ", t - time.time())
    print("all done")
    print(" outsde process res ", res[:])