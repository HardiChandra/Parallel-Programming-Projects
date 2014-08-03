Parallel-Programming-Projects
=============================
Project to parallelized some algorithms and see how much speedup can it get, using C*, C++ OpenMP, and Java Multi Threading

Project 1 : Using C* to parallelized merging two sorted list algorithms

Project 2 : Using C* to parallelized prime number sieve algorithms

Project 3 : Using C* to parallelized red black relaxation

Project 4 : Using C++ OpenMP to parallelized Bucket Sort

Project 5 : Using Java Multi Threading to parallelized merging two sorted list algorithms

Project 6 : Using Java Multi Threading & Replicated Worker to parallelized shortest path algorithms


Speedup results :
Project 1 : Using C* simulator, for 2000 data
Parralel Time =  1.057000000000E+004
Sequential Time =  3.017657000000E+006
Speedup =  2.846322392002E+002

Project 2 : Using C* simulator, for 10000 data
Sequential Time =  8.756940000000E+005
Parallel Time =  1.186600000000E+004
Speedup =  7.368916189835E+001

Project 3 : Using C* simulator 
Version 1 - Counter Variable, Speedup = 17.679
Version 2 - Local Barrier, Speedup = 31.923
Version 3 - Convergence Test, Speedup = 29.159

Project 4 : Using C++ Open MP Library with locking and 4 core processors
Version 1 - Single Spinlock, Speedup = 0.968
Version 2 - Fine-Grain Locking (Multiple Locks), Speedup = 1.846
Version 3 - Lock Free, Speedup = 2.103

Project 5 : Using Java Multi Threading and 2 core processors
Sequential Elapsed Time: 333
Parallel Elapsed Time: 175
Speedup = 1.9028572

Project 6 : Using Java Multi Threading with Replicated Worker and 2 core processors
Parallel Without Modification =	0.875
Parallel With Modification = 1.763
