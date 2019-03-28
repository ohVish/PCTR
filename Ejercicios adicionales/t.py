import multiprocessing


def f1(num, pipe):
	pipe[1].send(num)
	pipe[1].close

if __name__ == '__main__':
	pipe=multiprocessing.Pipe(True)
	p=multiprocessing.Process(target=f1,args=(10,pipe))
	p.start()
	p.join()
	try:
		while True :
			print(pipe[0].recv())
	except EOFError:
		pipe.close()
