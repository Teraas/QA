import time
from multiprocessing.pool import Pool

def cube(x):
    return x * x *x

def cubex(arr):
    res = []
    for x in arr:
        res.append(x * x *x)
    return res

if __name__ == '__main__':
    #pool
    t1 = time.time()
    arr = [22, 333, 555, 22, 44, 66, 88, 88]
    p = Pool(4)
    res = p.map(cube, arr, chunksize=1) # this is just like Map Reduce. devide tasks in to all the cores.
    print("result", res)
    print("time ", t1- time.time())

    t = time.time()
    res = cubex(arr)
    print("result", res)
    print("time ", t- time.time())
