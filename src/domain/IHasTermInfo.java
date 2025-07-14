/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

/**
 *
 * @author pc
 */
// domain/IHasTermInfo.java
import java.time.LocalDate;
import java.time.Period;

public interface IHasTermInfo {

    LocalDate getTermBeginDate();

    Period getTermPeriod();

    /**
     * Returns a fancy display string for the term period. Examples: - "1 day"
     * -> "Daily" - "14 days" -> "Fortnightly" - "1 month" -> "Monthly" - "3
     * months" -> "Every 3 months"
     *
     * @return
     */
    default String getTermPeriodDisplayText() {
        Period period = getTermPeriod();

        if (period == null || period.isZero()) {
            return "No term";
        }

        if (period.getDays() == 1 && period.getMonths() == 0 && period.getYears() == 0) {
            return "Daily";
        }

        if (period.getDays() == 14 && period.getMonths() == 0 && period.getYears() == 0) {
            return "Fortnightly";
        }

        if (period.getMonths() == 1 && period.getDays() == 0 && period.getYears() == 0) {
            return "Monthly";
        }

        if (period.getYears() > 0 && period.getMonths() == 0 && period.getDays() == 0) {
            return String.format("Yearly (%d year%s)", period.getYears(), period.getYears() > 1 ? "s" : "");
        }

        if (period.getMonths() > 1 && period.getDays() == 0 && period.getYears() == 0) {
            return String.format("Every %d months", period.getMonths());
        }

        return String.format("Term: %dY %dM %dD", period.getYears(), period.getMonths(), period.getDays());
    }
}
