/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// import libraries

/**
 *
 * @rolfspeaker
 */


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ElectricBillCalculator {
    public static void main(String[] args) {
        try (Scanner FileReaderService = new Scanner(new File("Customer_Data.txt"))) {

            String customer_id; String first_name;
            String last_name; int usage;
            String payment_overdue;

            if (!FileReaderService.hasNext()) {
                JOptionPane.showMessageDialog(
                    null, "ERROR: Attempt to index a nil value."
                ); return;
            }
            customer_id = FileReaderService.next();

            if (!FileReaderService.hasNext()) {
                JOptionPane.showMessageDialog(
                    null, "ERROR: Unable to retrieve a first name."
                ); return;
            }
            first_name = FileReaderService.next();

            if (!FileReaderService.hasNext()) {
                JOptionPane.showMessageDialog(
                    null, "ERROR: Unable to retrieve a last name."
                ); return;
            }
            last_name = FileReaderService.next();

            if (!FileReaderService.hasNext()) {
                JOptionPane.showMessageDialog(
                    null, "ERROR: Invalid electricity usage."
                ); return;
            }
            usage = FileReaderService.nextInt();

            if (!FileReaderService.hasNext()) {
                JOptionPane.showMessageDialog(
                    null, "ERROR: Unable to ascertain payment status."
                ); return;
            }
            payment_overdue = FileReaderService.next().toUpperCase();

            if (usage < 0) {
                JOptionPane.showMessageDialog(
                    null, "ERROR: Electricity usage cannot be negative."
                ); return;
            }

            if (!payment_overdue.equals("Y") && !payment_overdue.equals("N")) {
                JOptionPane.showMessageDialog(
                    null, "ERROR: Unsupported entry. Use 'Y' or 'N.'"
                ); return;
            }
                
            double electricity_charge; double late_payment_fee;
            double total_amount_due;

            if (usage <= 100) { electricity_charge = usage * 0.10; }
            else if (usage <= 300) { electricity_charge = 10.00 + (usage - 100) * 0.15; }
            else { electricity_charge = 40.00 + (usage - 300) * 0.20; }

            if (payment_overdue.equals("Y")){
                late_payment_fee = 15.00;
            } else {
                late_payment_fee = 0.00;
            }

            total_amount_due = electricity_charge + late_payment_fee;

            String message = String.format(
                "MONTHLY UTILITY BILL\n" +
                "================================\n" +
                "Customer ID: %s\n" +
                "Customer Name: %s %s\n" +
                "Electricity Usage: %d kWh\n" +
                "Electricity Charge: $%.2f\n" +
                "Late-Payment Fee: $%.2f\n" +
                "TOTAL AMOUNT DUE: $%.2f\n" +
                "Payment Status: %s",
                customer_id,
                first_name,
                last_name,
                usage,
                electricity_charge,
                late_payment_fee,
                total_amount_due,
                payment_overdue.equals("Y") ? "Previous bill... OVERDUE" : "Previous bill... paid"
            );

            JOptionPane.showMessageDialog(
                null, message
            );

            try (FileOutputStream FOS = new FileOutputStream("UtilityBillReport.txt"); DataOutputStream DOS = new DataOutputStream(FOS)) {
                    DOS.writeUTF(message);
                }

        } catch (FileNotFoundException PowerOutage) {
            JOptionPane.showMessageDialog(
                null, "ERROR: Customer data file not found."
            );

        } catch (Exception PowerOutage) {
            JOptionPane.showMessageDialog(
                null, "ERROR: " + PowerOutage.getMessage()
            );
        }
    }
}

