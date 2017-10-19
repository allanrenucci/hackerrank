import java.util.*;

class ConflictingAppointments {
	static class Appointment {
		final int start;
		final int end;

		public Appointment(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public boolean conflictsWith(Appointment that) {
			return !(this.end <= that.start || that.end <= this.start);
		}

		@Override
		public String toString() {
			return "[" + start + ", " + end + "]";
		}
	}

	public static void printConflictingAppointments(Appointment[] appointments) {
		for (int i = 0; i < appointments.length; ++i) {
			for (int j = i + 1; j < appointments.length; ++j) {
				Appointment ai = appointments[i];
				Appointment aj = appointments[j];
				if (ai.conflictsWith(aj)) {
					System.out.println(ai + "  conflicts with " + aj);
				}
			}
		}		
	}

	public static void printConflictingAppointments2(Appointment[] appointments) {
		if (appointments.length == 0) {
			return;
		}

		java.util.Arrays.sort(appointments, (a1, a2) -> Integer.compare(a1.start, a2.start));
		Appointment latestAppointment = appointments[0];


		for (int i = 1; i < appointments.length; ++i) {
			Appointment ai = appointments[i];

			if (ai.conflictsWith(latestAppointment)) {
				System.out.println(ai + "  conflicts with " + latestAppointment);
			}

			if (ai.end > latestAppointment.end) {
				latestAppointment = ai;
			}
		}

	}

	public static void main(String[] args) {
		Appointment[] appointments = {
			new Appointment(1, 5),
			new Appointment(3, 7),
			new Appointment(2, 6),
			new Appointment(100, 120),
			new Appointment(10, 15),
			new Appointment(5, 6),
			new Appointment(4, 100)
		};

		printConflictingAppointments2(appointments);
	}
}