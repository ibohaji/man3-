//Implementation of a basic Barrier class (skeleton)
//Mandatory assignment 3
//Course 02158 Concurrent Programming, DTU, Fall 2023

//Hans Henrik Lovengreen     Oct 26, 2023

class SafeBarrier extends Barrier {
    boolean active = false;
    int arrived = 0;
    boolean cond = true;
    boolean cond1 = false;


    public SafeBarrier(CarDisplayI cd) {
        super(cd);
    }

    @Override
    public synchronized void sync(int no) throws InterruptedException {
    if(active){

        while(cond1){
            wait();
        }

        arrived++;
        if(arrived == 9){
            cond = false;
            notifyAll();
        }

        while(cond){
            wait();
        }

        arrived-- ;
        cond1 = true;
        if(arrived == 0){
            cond = true;
            cond1 = false;
            notifyAll();
        }
    }
    }

    @Override
    public synchronized void on() {
        active = true;
    }

    @Override
    public synchronized void off() {
        active = false;
        arrived = 0;
        notifyAll();

    }

/*
    @Override
    // Here (ab)used for emulation of spurious wakeups
    public synchronized void set(int k) {
        for (int i = 0; i < k; i++) { notify(); }
    }    
*/    
    
}
