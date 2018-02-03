
import logging

import time
# from clockdeco import clock
from clockdeco2 import clock
import functools

def log(func):
    """
    给func装饰

    """
    logger = logging.getLogger(__name__)
    FORMAT = "[%(filename)s:%(lineno)s - %(funcName)20s() ] %(message)s"
    logging.basicConfig(format=FORMAT)
    logger.setLevel(logging.DEBUG)

    def logged(*arg, **kwargs):

        logger.info(func.__name__)
        result = func(*arg, **kwargs)
        return result
    return logged


@log
@clock
def snooze(seconds):
    time.sleep(seconds)

@clock
def factorial(n):
	return 1 if n < 2 else n * factorial(n - 1)

if __name__ == '__main__':

    print('*' * 40, 'Calling snooze(.123)')
    snooze(.123)
    print('*' * 40, 'Calling factorial(6)')
    print('6 != ', factorial(6))


"""
@clock
def factorial(n):
	return 1 if n < 2 else n * factorial(n - 1)
等价于

def factorial(n):
	return 1 if n < 2 else n * factorial(n - 1)

factorial = clock(factorial)
"""