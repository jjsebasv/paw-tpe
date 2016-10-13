package ar.edu.itba.paw.tags;


public class SemesterMapper {

    public static String mapSemester(final int semester) {

        final int year = (int) (semester / 2) + semester % 2;

        final int sem;

        if (semester % 2 == 1) {
            sem = 1;
        } else {
            sem = 2;
        }

        return String.format("Year %s - Semester %s", year, sem);
    }

}
