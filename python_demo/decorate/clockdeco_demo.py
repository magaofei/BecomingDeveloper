import time
# from clockdeco import clock
from clockdeco2 import clock
import functools

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
	print('6 != ', factorial(600))


"""
@clock
def factorial(n):
	return 1 if n < 2 else n * factorial(n - 1)
等价于

def factorial(n):
	return 1 if n < 2 else n * factorial(n - 1)

factorial = clock(factorial)
"""