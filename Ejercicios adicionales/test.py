import multiprocessing

def function(data):
	result=data*data
	return result

if __name__ == '__main__':
	inputs = list(range(100))
	pool=multiprocessing.Pool(processes=4)
	pool_output=pool.map(function,inputs)
	pool.close()
	pool.join()
	print('Pool:',pool_output)

