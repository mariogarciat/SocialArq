package co.edu.udea.compumovil.socialchallenge.bl;

/**
 * Created by steven on 12/11/16.
 */

public class ExperienceController {


    public int ExpProgress(int lvl, int exp) {

        int progress = 0;

        switch (lvl) {

            case 1:
                progress = (exp*100)/1500;
                break;
            case 2:
                progress = (exp*100)/2500;
                break;
            case 3:
                progress = (exp*100)/4500;
        }

        return progress;
    }
}
