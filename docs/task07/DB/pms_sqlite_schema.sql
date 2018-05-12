DROP TABLE IF EXISTS Addiction;
DROP TABLE IF EXISTS Appoinment;
DROP TABLE IF EXISTS Doctor;
DROP TABLE IF EXISTS Doctor_Appointment;
DROP TABLE IF EXISTS Drug;
DROP TABLE IF EXISTS Location;
DROP TABLE IF EXISTS Patient;
DROP TABLE IF EXISTS Patient_Addiction;
DROP TABLE IF EXISTS Patient_Appointment;
DROP TABLE IF EXISTS Patient_Doctor;
DROP TABLE IF EXISTS Patient_Drug;
DROP TABLE IF EXISTS Repetition;
DROP TABLE IF EXISTS User;

CREATE TABLE IF NOT EXISTS Location (
		ID integer PRIMARY KEY AUTOINCREMENT,
		Name varchar(30),
		Street varchar(30),
		"Post Code" integer,
		Place varchar(30),
		Telephone varchar(30),
		"E-Mail" varchar(30)
);


CREATE TABLE IF NOT EXISTS Patient (
		ID integer PRIMARY KEY AUTOINCREMENT, 
		Firstname varchar(30) NOT NULL,
		Lastname varchar(30) NOT NULL, 
		Street varchar(30), 
		"Post Code" integer, 
		Place varchar(30), 
		Telephone varchar(30), 
		LocationID integer NOT NULL,
		FOREIGN KEY(LocationID) REFERENCES Location(ID)
);

CREATE TABLE IF NOT EXISTS Drug (
		ID integer PRIMARY KEY AUTOINCREMENT,
		Name varchar(30)
);

CREATE TABLE IF NOT EXISTS Addiction (
		ID integer PRIMARY KEY AUTOINCREMENT,
		Name varchar(30),
		Description varchar(80)
);

CREATE TABLE IF NOT EXISTS User (
		ID integer PRIMARY KEY AUTOINCREMENT,
		Name varchar(30),
		password varchar(30),
		"Expiry Date" varchar(30)
);

CREATE TABLE IF NOT EXISTS Doctor (
		ID integer PRIMARY KEY AUTOINCREMENT,
		Firstname varchar(30),
		Lastname varchar(30),
		UserID integer NOT NULL,
		FOREIGN KEY(UserID) REFERENCES User(ID)
);

CREATE TABLE IF NOT EXISTS Appointment (
		ID integer PRIMARY KEY AUTOINCREMENT,
		Name varchar(30),
		Description varchar(80),
		"Start Date" varchar(30),
		"End Date" varchar(30),
		"Start Time" varchar(30),
		"End Time" varchar(30),
		LocationID integer,
		FOREIGN KEY(LocationID) REFERENCES Location(ID)
);

CREATE TABLE IF NOT EXISTS Repetition (
		AppointmentID integer PRIMARY KEY,
		Period varchar(30),
		FOREIGN KEY(AppointmentID) REFERENCES Appointment(ID)
);

CREATE TABLE IF NOT EXISTS Doctor_Appointment (
		DoctorID integer,
		AppointmentID integer,
		PRIMARY KEY(DoctorID, AppointmentID),
		FOREIGN KEY(DoctorID) REFERENCES Doctor(ID),
		FOREIGN KEY(AppointmentID) REFERENCES Appointment(ID)
);

CREATE TABLE IF NOT EXISTS Patient_Appointment (
		PatientID integer,
		AppointmentID integer,
		PRIMARY KEY(PatientID, AppointmentID),
		FOREIGN KEY(PatientID) REFERENCES Patient(ID),
		FOREIGN KEY(AppointmentID) REFERENCES Appointment(ID)
);

CREATE TABLE IF NOT EXISTS Patient_Doctor (
		PatientID integer,
		DoctorID integer,
		PRIMARY KEY(PatientID, DoctorID),
		FOREIGN KEY(PatientID) REFERENCES Patient(ID),
		FOREIGN KEY(DoctorID) REFERENCES Doctor(ID)
);

CREATE TABLE IF NOT EXISTS Patient_Addiction (
		PatientID integer,
		AddictionID integer,
		PRIMARY KEY(PatientID, AddictionID),
		FOREIGN KEY(PatientID) REFERENCES Patient(ID),
		FOREIGN KEY(AddictionID) REFERENCES Addiction(ID)
);

CREATE TABLE IF NOT EXISTS Patient_Drug (
		PatientID integer,
		DrugID integer,
		Dose integer,
		Measurement varchar(2),
		PRIMARY KEY(PatientID, DrugID),
		FOREIGN KEY(PatientID) REFERENCES Patient(ID),
		FOREIGN KEY(DrugID) REFERENCES Drug(ID)
);

INSERT INTO Location(Name, Street, "Post Code", Place, Telephone, "E-Mail") Values ("Toni", "Test", 3014, "Test", "Test", "Test");
INSERT INTO Location(Name, Street, "Post Code", Place, Telephone, "E-Mail") Values ("Toni2", "Test2", 3014, "Test2", "Test2", "Test2");
