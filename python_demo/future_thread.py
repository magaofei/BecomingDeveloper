"""
结论:
1. 计算型任务不开线程,开线程有消耗,反而慢

"""
from concurrent import futures
import threading
import time

MAX_WORKERS = 100

num = 0

def plus_num():
    global num, lock
    
    lock.acquire()
    for i in range(10):
        num = num + 1
        print(num)
    lock.release()

def plus_num_no_lock():
    global num
    
    for i in range(10):
        num = num + 1
        print(num)
    # num += 1
        # print(num)
    # return num

def plus_num2_no_lock():
    global num
    for i in range(10):
        num = num + 1
        print(num)

def plus_num2():
    global num, lock
    lock.acquire()
    for i in range(10):
        num = num + 1
        print(num)
    lock.release()
    # num += 1

# r = plus_num()
# print(r)

def thread_pool_executor():
    """
    """
    with futures.ThreadPoolExecutor(MAX_WORKERS) as executor:
        to_do = []
        res = executor.submit(plus_num_no_lock)
        res2 = executor.submit(plus_num2_no_lock)
        to_do.append(res)
        to_do.append(res2)
    
        for future in futures.as_completed(to_do):
            res = future.result()
            print(res)
        # print(res.result())
    

def threading_demo():
    """
    """
    threads = []
    for i in range(10):
        i = threading.Thread(target=plus_num_no_lock)
        i2 = threading.Thread(target=plus_num2_no_lock)
        i.start()
        i2.start()
        threads.append(i)
        threads.append(i2)
    
    [t.join() for t in threads]
    
    print(num)
    
if __name__ == '__main__':
    t0 = time.time()
    lock = threading.Lock()
    # threading_demo()
    thread_pool_executor()
    t1 = time.time()
    print(t1 - t0)
    






# print(num)





