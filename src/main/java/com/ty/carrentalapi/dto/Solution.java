package com.ty.carrentalapi.dto;


import java.util.Scanner;

public class Solution {
	
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Entry time");
			double a = scanner.nextDouble();

			System.out.println("Exit time");
			double b = scanner.nextDouble();

			Time time = new Time();

			//  1<3                    4<5
			if (a < time.startTime && b < time.endTime) {
				System.out.println("new slot booked");
			} else if (a > time.endTime) {
				System.out.println("new Slot booked");
			} else {
				System.out.println("Slot already booked");
			}
		}
	}



