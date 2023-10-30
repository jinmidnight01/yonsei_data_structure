/*
 * Name: Jinhyo Park
 * Student ID #: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class Scheduler implements IScheduler {
    /*
     * you may declare variables here
     */
    Heap<JobWrapper> jobPool;

    Scheduler() {
        /*
         * implement your constructor here.
         */
        jobPool = new Heap<>();
    }

    @Override
    public void register(IJob j) {
        /*
         * Function input:
         *  + j: instance of class implementing IJob.
         *
         * Does:
         * add j to the job pool.
         */
        jobPool.insert(new JobWrapper(j));
    }

    @Override
    public IJob process() {
        /*
         * Function input:
         *  none
         *
         * Does:
         * serve the job with the highest priority once.
         * between two jobs, a job has a higher priority if its patience is lower than the other job.
         * if they have the same patience, the one with the smaller pid has the higher priority.
         * if the job is done, then remove it from the job pool and return the job.
         * otherwise, do not remove the served job from the job pool and return null.
         */
        JobWrapper jw = jobPool.removeMin();
        if (jw != null) {
            IJob j = jw.getJob();
            j.serve();
            if (j.isDone()) {
                return j;
            } else {
                jobPool.insert(jw);
            }
        }

        return null;
    }

    private class JobWrapper implements Comparable<JobWrapper> {
        private IJob job;

        JobWrapper(IJob j) {
            job = j;
        }

        public IJob getJob() {
            return job;
        }

        @Override
        public int compareTo(JobWrapper other) {
            if (job.getPatience() < other.getJob().getPatience()) {
                return -1;
            } else if (job.getPatience() > other.getJob().getPatience()) {
                return 1;
            } else {
                if (job.getPid() < other.getJob().getPid()) {
                    return -1;
                } else if (job.getPid() > other.getJob().getPid()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

    }
}
