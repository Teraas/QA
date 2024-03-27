import threading
import time

def square(arr):
    for x in arr:
        time.sleep(1)
        print(x *x)
def cube(arr):
    for x in arr:
        time.sleep(1)
        print(x * x * x)
if __name__ == '__main__':
    arr = [1,2,3,4,5,6,7,8,9,10]
    t = time.time()
    t1 = threading.Thread(target = square, args=(arr,))
    t2 = threading.Thread(target = cube, args=(arr,))
    t1.start()
    t2.start()
    t1.join()
    t2.join()

    print("time take", t - time.time())
    print("all done")