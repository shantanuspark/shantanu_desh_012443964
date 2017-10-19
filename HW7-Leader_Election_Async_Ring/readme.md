This is an implementation for leader election in asynchronous rings. The complexity is O(n^2).

I have taken 5 processors and implemented them in a custom ring data structure. The processors are executed as threads and run using executor service.

Output is as follows: The formed ring is 44-->25-->32-->56-->99-->

Initiaing election process... Initiating election by processor 44 Initiating election by processor 25 Initiating election by processor 32 Initiating election by processor 56 Initiating election by processor 99 Leader is 99 Leader is 99 Leader is 99 Leader is 99 Leader is 99
