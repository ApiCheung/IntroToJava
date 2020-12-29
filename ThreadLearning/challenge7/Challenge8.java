package ThreadLearning.challenge7;

/**
 * deadlock
 * 出现的情况
 * 1。 is a set of liocks being obtained ina different order by
 * multip;e thread
 * 2. oversynchronizing
 * 3 rewirete the code to break any circular call patterns
 * 4 would using reentrantlcok object?
 */
public class Challenge8 {
    public static void main(String[] args) {
        final Tutor tutor = new Tutor();
        final Student student = new Student(tutor);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });

        tutorThread.start();
        studentThread.start();
    }
}

class Tutor {
    private Student student;

    public synchronized void setStudent(Student student) {
        this.student = student;
    }

    public void studyTime() {
        System.out.println("Tutor has arrived");
        synchronized (student) {
            try {
                // wait for student to arrive and hand in assignment
                Thread.sleep(300);
            } catch (InterruptedException e) {

            }
            student.startStudy();
            System.out.println("Tutor is studying with student");
        }
    }

    public synchronized void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}

class Student {

    private Tutor tutor;

    Student(Tutor tutor) {
        this.tutor = tutor;
    }

    public synchronized void startStudy() {
        // study
        System.out.println("Student is studying");
    }

    public synchronized void handInAssignment() {
        tutor.getProgressReport();
        System.out.println("Student handed in assignment");
    }
}