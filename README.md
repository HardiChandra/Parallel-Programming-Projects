Parallel-Programming-Projects
=============================
Project to parallelized some algorithms and see how much speedup can it get, using C*, C++ OpenMP, and Java Multi Threading

Project 1 : Using C* to parallelized merging two sorted list algorithms<br />
Project 2 : Using C* to parallelized prime number sieve algorithms<br />
Project 3 : Using C* to parallelized red black relaxation<br />
Project 4 : Using C++ OpenMP to parallelized Bucket Sort<br />
Project 5 : Using Java Multi Threading to parallelized merging two sorted list algorithms<br />
Project 6 : Using Java Multi Threading & Replicated Worker to parallelized shortest path algorithms

Speedup results :
Project 1 : Using C* simulator, for 2000 data<br />
Parralel Time =  1.057000000000E+004<br />
Sequential Time =  3.017657000000E+006<br />
Speedup =  2.846322392002E+002<br />

Project 2 : Using C* simulator, for 10000 data<br />
Sequential Time =  8.756940000000E+005<br />
Parallel Time =  1.186600000000E+004<br />
Speedup =  7.368916189835E+001<br />

Project 3 : Using C* simulator <br />
Version 1 - Counter Variable, Speedup = 17.679<br />
Version 2 - Local Barrier, Speedup = 31.923<br />
Version 3 - Convergence Test, Speedup = 29.159<br />

Project 4 : Using C++ Open MP Library with locking and 4 core processors<br />
Version 1 - Single Spinlock, Speedup = 0.968<br />
Version 2 - Fine-Grain Locking (Multiple Locks), Speedup = 1.846<br />
Version 3 - Lock Free, Speedup = 2.103<br />

Project 5 : Using Java Multi Threading and 2 core processors<br />
Sequential Elapsed Time: 333<br />
Parallel Elapsed Time: 175<br />
Speedup = 1.9028572<br />

Project 6 : Using Java Multi Threading with Replicated Worker and 2 core processors<br />
Parallel Without Modification =	0.875<br />
Parallel With Modification = 1.763<br />
