import multiprocessing
import threading
from threading import Thread
import concurrent.futures
import time
from queue import Queue
from multiprocessing.pool import Pool
import asyncio

obj = threading.Semaphore(2)

def printArray(arr):
    #sleep(10)
    for x in arr:
        a = x * x *x
        time.sleep(0.2)
        print(x)
        #sleep(1)
    print("thread is done")

def printArraySem(arr, lock):
    #obj.acquire()
    for x in arr:
        with lock:
            print(x)
    print("thread is done")
    #obj.release()

def test2():
    queue = [1,2,3,4,5,6,7,8,9,10]
    #Queue()
    for x in range(3):
        worker = RunWorker(queue)
        # Setting daemon to True will let the main thread exit even though the workers are blocking
        worker.daemon = True
        worker.start()
def testPool():
    arr = [1,2,3,4,5,6,7,8,9,10]
    with Pool(1) as p:
        for x in arr:
            p.map(print, [x])
            p.map(print, [x])

def testConc():
    arr = [1,2,3,4,5,6,7,8,9,10]
    with concurrent.futures.ThreadPoolExecutor(max_workers=2) as executor:
        # Submit the same method for execution twice
        for x in arr:
            future1 = executor.submit(print(x))

            future2 = executor.submit(print(x))

            # Wait for both futures to complete
            concurrent.futures.wait([future1, future2])


def print1(x):
    #obj.acquire()
    print(x)
    #obj.release()

async def print_parallel():
    my_array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    tasks = [
        print_integer_from_array(my_array),
        print_integer_from_array(my_array),
    ]

    await asyncio.gather(*tasks)

async def print_integer_from_array(nums):
    for num in nums:
        print(num)
        await asyncio.sleep(0)  # Allow event loop to switch tasks

class PrintThread(threading.Thread):
    def __init__(self, nums, start_index, step, lock):
        super(PrintThread, self).__init__()
        self.nums = nums
        self.start_index = start_index
        self.step = step
        self.lock = lock

    def run(self):
        for i in range(self.start_index, len(self.nums), self.step):
            with self.lock:
                print(self.nums[i])

def print_array_with_threads(nums):
    lock = threading.Lock()

    thread1 = PrintThread(nums, 0, 2, lock)
    thread2 = PrintThread(nums, 1, 2, lock)

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

if __name__ == '__main__':
    arr = [1,2,3,4,5,6,7,8,9,10]
    lock = threading.Lock()
    t = time.time()
    t1 = threading.Thread(target=printArray, args=(arr,))
    #t1 = threading.Thread(target=printArraySem, args=(arr,lock))

    t2 = threading.Thread(target=printArray, args=(arr,))
    #t2 = threading.Thread(target=printArraySem, args=(arr,lock))

    t1.start()
    t2.start()
    t1.join()
    t2.join()
    print("time take", t - time.time())
    #testPool()
    #print_array_with_threads(arr)
    #multiprocessing.set_start_method('spawn')
    #testConc()
    #asyncio.run(print_parallel())
    print("all ts done")


class RunWorker(Thread):

    def __init__(self, queue):
        Thread.__init__(self)
        self.queue = queue

    def run(self):
        for x in self.queue:
            print(x)
        print("thread is done")
