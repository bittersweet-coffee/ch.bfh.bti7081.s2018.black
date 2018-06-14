
INSERT INTO doctor (firstname, lastname, username, passwordhash) VALUES ('Antonio', 'Musardo', 'musardoa', '$2a$10$r6Mkf90C3vnL33aME6oR9uAuWUzjfjbQCCBdVkGsBEBor2DzZEjJ6')
INSERT INTO doctor (firstname, lastname, username, passwordhash) VALUES ('Nicola', 'Schlup', 'schlupn', '$2a$10$r6Mkf90C3vnL33aME6oR9uAuWUzjfjbQCCBdVkGsBEBor2DzZEjJ6')
INSERT INTO doctor (firstname, lastname, username, passwordhash) VALUES ('Cedric', 'Bielmann', 'bielmannc', '$2a$10$r6Mkf90C3vnL33aME6oR9uAuWUzjfjbQCCBdVkGsBEBor2DzZEjJ6')
INSERT INTO doctor (firstname, lastname, username, passwordhash) VALUES ('Amin', 'Schaller', 'schallera', '$2a$10$r6Mkf90C3vnL33aME6oR9uAuWUzjfjbQCCBdVkGsBEBor2DzZEjJ6')
INSERT INTO doctor (firstname, lastname, username, passwordhash) VALUES ('Michael', 'Hofer', 'hoferm', '$2a$10$r6Mkf90C3vnL33aME6oR9uAuWUzjfjbQCCBdVkGsBEBor2DzZEjJ6')
INSERT INTO doctor (firstname, lastname, username, passwordhash) VALUES ('Aleksandar', 'Andrejic', 'andrejica', '$2a$10$r6Mkf90C3vnL33aME6oR9uAuWUzjfjbQCCBdVkGsBEBor2DzZEjJ6')
INSERT INTO doctor (firstname, lastname, username, passwordhash) VALUES ('Jan', 'Henzi', 'henzij', '$2a$10$r6Mkf90C3vnL33aME6oR9uAuWUzjfjbQCCBdVkGsBEBor2DzZEjJ6')


INSERT INTO addiction (name, description) VALUES('Alcoholism', 'Alcoholism, also known as alcohol use disorder (AUD), is a broad term for any drinking of alcohol that results in mental or physical health problems. The disorder was previously divided into two types: alcohol abuse and alcohol dependence. In a medical context, alcoholism is said to exist when two or more of the following conditions is present: a person drinks large amounts over a long time period, has difficulty cutting down, acquiring and drinking alcohol takes up a great deal of time, alcohol is strongly desired, usage results in not fulfilling responsibilities, usage results in social problems, usage results in health problems, usage results in risky situations, withdrawal occurs when stopping, and alcohol tolerance has occurred with use.')
INSERT INTO addiction (name, description) VALUES('Cocaine Dependence', 'Cocaine dependence is a psychological desire to use cocaine regularly. Cocaine overdose may result in cardiovascular and brain damage, such as: constricting blood vessels in the brain, causing strokes and constricting arteries in the heart; causing heart attacks. The use of cocaine creates euphoria and high amounts of energy. If taken in large, unsafe doses, it is possible to cause mood swings, paranoia, insomnia, psychosis, high blood pressure, a fast heart rate, panic attacks, cognitive impairments and drastic changes in personality.')
INSERT INTO addiction (name, description) VALUES('Problem Gambling', 'Problem gambling (or ludomania, but usually referred to as "gambling addiction" or "compulsive gambling") is an urge to gamble continuously despite harmful negative consequences or a desire to stop.')
INSERT INTO addiction (name, description) VALUES('Workaholism','The so-called work addiction describes the clinical picture of a "workaholic", one for his (supposed) well-being, his superficial health and satisfaction or his apparent success from the practice of work in the medical sense dependent people."Work addiction" is thus a "substance-free addiction" in which a compulsive attitude to performance and work is developed, with all the medical and psychological consequences and secondary diseases known from other dependence diseases. Work addicts live more or less exclusively for their work; the focus is usually on quality and quantity, but not on the meaning or meaning of the work to be done, and a perfectionist attitude is implemented.')
INSERT INTO addiction (name, description) VALUES('Hypersexuality','Hypersexuality is defined as exhibiting unusual or excessive concern with or indulgence in sexual activity. Hypersexuality is generally associated with hypomania and mania and used to be known as nymphomania. It should be noted that the severity of hypersexuality runs the gamut just like all hypomanic /manic symptoms do.')
INSERT INTO addiction (name, description) VALUES('Gaming Addiction','Instead of devoting energy to "real-world" activities and pursuits, a video game addict spends most of his or her time playing games. Someone who has developed a video game addiction prioritizes gaming accomplishments over all other activities such as spending time with friends and family, school achievement, work performance, and interpersonal relationships.')
INSERT INTO addiction (name, description) VALUES('Food Addiction', 'A food addiction or eating addiction is a behavioral addiction that is characterized by the compulsive consumption of palatable (e.g., high fat and high sugar) foods – the types of food which markedly activate the reward system in humans and other animals – despite adverse consequences')
INSERT INTO addiction (name, description) VALUES('Caffeine dependence', 'Caffeine is a commonplace central nervous system stimulant drug which occurs in nature as part of the coffee, tea, yerba mate and other plants. It is also an additive in many consumer products, most notably beverages advertised as energy drinks. Caffeine is also added to sodas such as Coca-Cola and Pepsi, where, on the ingredients listing, it is designated as a flavoring agent, due to pure caffeine powder having a bitter flavour')
INSERT INTO addiction (name, description) VALUES('Amphetamine Addiction','Addiction is a serious risk with heavy recreational amphetamine use but is unlikely to arise from typical long-term medical use at therapeutic doses. Drug tolerance develops rapidly in amphetamine abuse, so periods of extended use require increasingly larger doses of the drug in order to achieve the same effect')
INSERT INTO addiction (name, description) VALUES('Compulsive buying disorder', 'Compulsive buying disorder (CBD), is characterized by an obsession with shopping and buying behavior that causes adverse consequences. According to Kellett and Bolton, compulsive buying "is experienced as an irresistible–uncontrollable urge, resulting in excessive, expensive and time-consuming retail activity that is typically prompted by negative affectivity" and results in "gross social, personal and/or financial difficulties".')

INSERT INTO symptom (description, addiction_id) VALUES ('Drinks large amounts over a long period', 1)
INSERT INTO symptom (description, addiction_id) VALUES ('Difficulty cutting down', 1)
INSERT INTO symptom (description, addiction_id) VALUES ('Acquiring and drinking alcohol takes up a lot of time', 1)
INSERT INTO symptom (description, addiction_id) VALUES ('Usage results in problems', 1)
INSERT INTO symptom (description, addiction_id) VALUES ('Withdrawal occurs when stopping', 1)
INSERT INTO symptom (description, addiction_id) VALUES ('Alcohol tolerance has occurred', 1)
INSERT INTO symptom (description, addiction_id) VALUES ('Aggression', 2)
INSERT INTO symptom (description, addiction_id) VALUES ('Severe paranoia', 2)
INSERT INTO symptom (description, addiction_id) VALUES ('Restlessness', 2)
INSERT INTO symptom (description, addiction_id) VALUES ('Confusion', 2)
INSERT INTO symptom (description, addiction_id) VALUES ('Tactile hallucinations', 2)
INSERT INTO symptom (description, addiction_id) VALUES ('Suicide thoughts', 2)
INSERT INTO symptom (description, addiction_id) VALUES ('Unusal weith loss', 2)
INSERT INTO symptom (description, addiction_id) VALUES ('Trouble maintaining relationships', 2)
INSERT INTO symptom (description, addiction_id) VALUES ('Unhealthy pale appearance', 2)
INSERT INTO symptom (description, addiction_id) VALUES ('Need to gamble with increasing amounts of money in order to achieve the desired excitement', 3)
INSERT INTO symptom (description, addiction_id) VALUES ('Restless or irritable when attempting to cut down or stop gambling', 3)
INSERT INTO symptom (description, addiction_id) VALUES ('Repeated unsuccessful efforts to control, cut back, or stop gambling', 3)
INSERT INTO symptom (description, addiction_id) VALUES ('Often preoccupied with gambling', 3)
INSERT INTO symptom (description, addiction_id) VALUES ('Gambling when feeling distressed', 3)
INSERT INTO symptom (description, addiction_id) VALUES ('Lying to conceal the extent of involvement with gambling', 3)
INSERT INTO symptom (description, addiction_id) VALUES ('Jeopardize or loss a significant relationship, job, education or career opportunity because of gambling', 3)
INSERT INTO symptom (description, addiction_id) VALUES ('Relying on others to provide money to relieve desperate financial situations caused by gambling', 3)
INSERT INTO symptom (description, addiction_id) VALUES ('Above-average work input',4)
INSERT INTO symptom (description, addiction_id) VALUES ('hyperbolic dysfunctional striving for perfection',4)
INSERT INTO symptom (description, addiction_id) VALUES ('excessive masturbation',5)
INSERT INTO symptom (description, addiction_id) VALUES ('daily pornography consumption',5)
INSERT INTO symptom (description, addiction_id) VALUES ('frequently changing sex partners',5)
INSERT INTO symptom (description, addiction_id) VALUES ('Significant negative impact on work performance, school achievement, and / or interpersonal relationships',6)
INSERT INTO symptom (description, addiction_id) VALUES ('Spending most of ones free time playing video games',6)
INSERT INTO symptom (description, addiction_id) VALUES ('Frequently playing video games for six to eight hours non-stop',6)
INSERT INTO symptom (description, addiction_id) VALUES ('Loss of interest in social activities',6)
INSERT INTO symptom (description, addiction_id) VALUES ('Avoidance of personal responsibilities or commitments so that gaming can continue - Often staying up very late to play video games which regularly leads to feeling very fatigued the next day',6) 
INSERT INTO symptom (description, addiction_id) VALUES ('Eating an unhealthy amount of food while feeling that ones sense of control has been lost', 7)
INSERT INTO symptom (description, addiction_id) VALUES ('Continuously eat throughout the day', 7)
INSERT INTO symptom (description, addiction_id) VALUES ('Consuming food quickly', 7)
INSERT INTO symptom (description, addiction_id) VALUES ('Gaining weight rapidly', 7)
INSERT INTO symptom (description, addiction_id) VALUES ('Eating to the point of feeling sick to the stomach',7)
INSERT INTO symptom (description, addiction_id) VALUES ('headaches',8)
INSERT INTO symptom (description, addiction_id) VALUES ('muscle pain and stiffness',8)
INSERT INTO symptom (description, addiction_id) VALUES ('lethargy',8)
INSERT INTO symptom (description, addiction_id) VALUES ('nausea',8)
INSERT INTO symptom (description, addiction_id) VALUES ('vomiting',8)
INSERT INTO symptom (description, addiction_id) VALUES ('depressed mood',8)
INSERT INTO symptom (description, addiction_id) VALUES ('marked irritability.',8)
INSERT INTO symptom (description, addiction_id) VALUES ('Increased heart rate and blood pressure',9)
INSERT INTO symptom (description, addiction_id) VALUES ('Decreased appetite and weight loss',9)
INSERT INTO symptom (description, addiction_id) VALUES ('Insomnia',9)
INSERT INTO symptom (description, addiction_id) VALUES ('Digestive upset',9)
INSERT INTO symptom (description, addiction_id) VALUES ('Mood swings',9)
INSERT INTO symptom (description, addiction_id) VALUES ('Aggression',9)
INSERT INTO symptom (description, addiction_id) VALUES ('Paranoia and anxiety',9)
INSERT INTO symptom (description, addiction_id) VALUES ('Visual, auditory, or tactile hallucinations',9)
INSERT INTO symptom (description, addiction_id) VALUES ('Inability to keep up with work, school, or home responsibilities',9)
INSERT INTO symptom (description, addiction_id) VALUES ('Over-preoccupation with buying',10)
INSERT INTO symptom (description, addiction_id) VALUES ('distress or impairment as a result of the activity',10)
INSERT INTO symptom (description, addiction_id) VALUES ('compulsive buying is not limited to hypomanic or manic episodes',10)


INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@BernerKlinikMontana.ch','Berner Klinik Montana','Bern',1000,'Aarbergergasse','062 287 32 32')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@BernerRehaZentrum.ch','Berner Reha Heiligenschwendi','Bern',1003,'Aarstrasse','081 303 30 30')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@HirslandenKlinikBeauite.ch','Hirslanden Klinik Beau-Site','Bern',1004,'Abendstrasse','0848 801 100')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@HirslandenKlinikPermanence.ch','Hirslanden Klinik Permanence','Bern',1005,'Aberlistrasse','044 786 00 00')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@HirslandenSalemSpital.ch','Hirslanden Salem-Spital','Bern',1006,'Aebistrasse','062 287 32 32')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@HôpitalduJurabernois.ch','Hopital du Jura bernois Moutier','Bern',1007,'Aegertenstrasse','062 767 99 99')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@HôpitalduJurabernoisSaintImier.ch','Hopital du Jura bernois Saint-Imier','Bern',1008,'Aehrenweg','044 814 27 74')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@HUGHôpitaldeLoëx.ch','HUG Hopital de Loex','Bern',1008,'Ahornweg','052 208 08 08')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@Inselspital.ch','Inselspital','Bern',1009,'Alemannenstrasse','071 913 87 00')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@PallasKlinikBern.ch','Pallas Klinik Bern','Bern',1010,'Alexandraweg','043 883 12 34')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@ServicesPsychiatriquesJuraBernois.ch','Services Psychiatriques Jura','Bern',1011,'Alleeweg','062 287 32 32')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@SoteriaBern.ch','Soteria Bern','Bern',1012,'Allmendstrasse','081 303 30 30')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@StiftungDiaconisPalliativeCareDiakonissenhaus.ch','Stiftung Diaconis Palliative Care','Bern',1015,'Alpeneggstrasse','0848 801 100')
INSERT INTO clinic (email, name, place, post_code, street, telephone) VALUES('request@upd.ch','UPD Waldau','Bern',1018,'Alpenstrasse','044 786 00 00')


INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Dominik','Mueller','1994-03-17',1000,'Aarbergergasse','062 287 32 32',1)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Xaver','Schmidt','1990-04-23',1003,'Aarstrasse','081 303 30 30',1)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Laurenz','Schneider','1991-05-06',1004,'Abendstrasse','0848 801 100',2)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Jannes','Fischer','1992-02-15',1005,'Aberlistrasse','044 786 00 00',3)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Mark','Weber','1995-09-27',1006,'Aebistrasse','062 287 32 32',4)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Mike','Meyer','1997-12-01',1007,'Aegertenstrasse','062 767 99 99',5)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Alexandra','Wagner','1998-11-01',1008,'Aehrenweg','044 814 27 74',6)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Isabella','Becker','1995-10-26',1008,'Ahornweg','052 208 08 08',7)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Emilia','Schulz','1996-06-26',1009,'Alemannenstrasse','071 913 87 00',8)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Astrid','Hoffmann','1993-04-08',1010,'Alexandraweg','043 883 12 34',9)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Gianna','Schaefer','1999-03-09',1011,'Alleeweg','062 287 32 32',10)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Adina','Koch','1997-02-25',1012,'Allmendstrasse','081 303 30 30',11)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Julie','Bauer','1993-01-01',1015,'Alpeneggstrasse','0848 801 100',12)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Chloe','Richter','1999-07-07',1018,'Alpenstrasse','044 786 00 00',1)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Stella','Klein','1994-06-05',1020,'Altenbergrain','062 287 32 32',1)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Evelyne','Wolf','1995-08-13',1022,'Altenbergstrasse','062 767 99 99',2)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Eva','Schroeder','1992-09-05',1023,'Alter Aargauerstalden','044 814 27 74',3)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Leyla','Neumann','1997-12-13',1024,'Amietstrasse','052 208 08 08',4)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Rose','Schwarz','1999-01-12',1025,'Amselweg','071 913 87 00',5)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Britt','Zimmermann','1993-09-01',1000,'Amthausgässchen','043 883 12 34',6)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Juliana','Braun','1998-08-08',1003,'Amthausgasse','062 287 32 32',7)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Aglaja','Krueger','1998-02-17',1004,'Anemonenweg','081 303 30 30',8)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Linea','Hofmann','1994-08-27',1005,'Ankerstrasse','0848 801 100',9)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Aleyna','Hartmann','1992-03-14',1006,'Anshelmstrasse','044 786 00 00',10)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Eline','Lange','1990-05-05',1007,'Archivstrasse','062 287 32 32',11)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Selin','Schmitt','1993-03-18',1008,'Armandweg','062 767 99 99',12)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Hildegard','Werner','1994-05-19',1008,'Asterweg','044 814 27 74',1)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Jonna','Schmitz','1990-06-03',1009,'Asylweg','052 208 08 08',1)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Jeanne','Krause','1992-06-22',1010,'Attinghausenstrasse','071 913 87 00',2)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Anita','Meier','1999-11-02',1011,'Badgasse','043 883 12 34',3)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Adriana','Lehmann','1999-07-23',1012,'Bäckereiweg','062 287 32 32',4)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Paulina','Schmid','2000-01-01',1015,'Bärenplatz','081 303 30 30',5)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Avelina','Schulze','1996-09-15',1018,'Bahnhöheweg','0848 801 100',6)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Teo','Maier','1993-09-21',1020,'Bahnhofplatz','044 786 00 00',7)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Raffael','Koehler','1992-02-22',1022,'Bahnstrasse','062 287 32 32',8)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Jon','Koenig','1992-02-08',1023,'Balderstrasse','062 767 99 99',9)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Maximilian','Walter','1994-03-05',1024,'Balmerstrasse','044 814 27 74',10)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Viktor','Mayer','1998-08-28',1025,'Balmweg','052 208 08 08',11)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Léon','Huber','1996-04-04',1000,'Balthasarstrasse','071 913 87 00',12)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Okke','Kaiser','1999-11-11',1003,'Baltzerstrasse','043 883 12 34',1)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Sergio','Fuchs','1991-10-22',1004,'Bantigerstrasse','062 287 32 32',1)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Ulf','Peters','1991-02-07',1005,'Bantigerweg','081 303 30 30',2)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Frank','Lang','1991-10-13',1006,'Baumgartenstrasse','0848 801 100',3)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Mischa','Scholz','1991-05-24',1007,'Beatusstrasse','044 786 00 00',4)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Milan','Mueller','1999-01-24',1008,'Beaulieurain','062 287 32 32',5)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Alfred','Weiß','1994-06-24',1008,'Beaulieustrasse','062 767 99 99',6)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Jerome','Jung','1995-03-14',1009,'Beaumontweg','044 814 27 74',7)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Marlon','Hahn','1997-06-30',1010,'Belpstrasse','052 208 08 08',8)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Erik','Schubert','1993-01-22',1011,'Benteliweg','071 913 87 00',9)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Theodor','Vogel','1989-12-12',1012,'Berchtoldstrasse','043 883 12 34',10)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Friedrich','Lappen','1991-05-19',1015,'Bernastrasse','062 287 32 32',11)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Aris','Keller','1993-05-19',1018,'Bernstrasse','081 303 30 30',12)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Laurent','Guenther','1999-09-19',1020,'Bethlehemstrasse','0848 801 100',1)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Etienne','Frank','1998-08-15',1022,'Beundenfeldstrasse','044 786 00 00',1)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Steven','Berger','1992-02-20',1023,'Bibliothekgässchen','062 287 32 32',2)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Flavio','Winkler','1944-06-12',1024,'Biderstrasse','062 767 99 99',3)
INSERT INTO patient (firstname, lastname, birthday, post_code, street, telephone, clinic_id) VALUES('Shawn','Roth','1991-11-25',1025,'Bienenstrasse','044 814 27 74',4)


INSERT INTO drug (name, description, minDose, maxDose, measure, unit) values ('Amlodipine', 'Amlodipine is a calcium channel blocker that dilates (widens) blood vessels and improves blood flow.', 0.5, 2.5, 'DOSE_DOUBLE', 'UNIT_MILLIGRAM')
INSERT INTO drug (name, description, minDose, maxDose, measure, unit) values ('Aspirin', 'Aspirin is a salicylate (sa-LIS-il-ate). It works by reducing substances in the body that cause pain, fever, and inflammation.', 2, 5, 'DOSE_INTEGER', 'UNIT_MILLIGRAM')
INSERT INTO drug (name, description, minDose, maxDose, measure, unit) values ('Austedo', 'Austedo (deutetrabenazine) reduces the amount of certain chemicals in the body that are overly active in people with Huntingtons disease.', 4.5, 9.5,'DOSE_HALVES', 'UNIT_PILL')
INSERT INTO drug (name, description, minDose, maxDose, measure, unit) values ('Methadone','As an opioid, methadone has the same effect and side effect profile and thus essentially the same risk potential as other opioids. However, due to the slow flooding during oral application, it does not produce a kick (the sudden intense well-being that also leads to the development of an addiction).', 0.8, 2.2, 'DOSE_DOUBLE', 'UNIT_MILLIGRAM')
INSERT INTO drug (name, description, minDose, maxDose, measure, unit) values ('Buprenorphine','In the substitution therapy of opioid-dependent mostly heroin-dependent patients with buprenorphine, it should be noted that the switch to buprenorphine - especially if there are still significant amounts of other opioids in the body and buprenorphine is given in too low doses - can lead to increased withdrawal symptoms due to its partially antagonistic character.', 0.1, 1.4, 'DOSE_DOUBLE', 'UNIT_MILLIGRAM')
INSERT INTO drug (name, description, minDose, maxDose, measure, unit) values ('Ritalin','Methylphenidate is a drug from the group of phenylethylamines. It has a stimulating effect and is now mainly used for the treatment of attention deficit/hyperactivity disorder (ADHD) and less frequently for narcolepsy', 2.5, 3.5,'DOSE_HALVES', 'UNIT_PILL')
INSERT INTO drug (name, description, minDose, maxDose, measure, unit) values ('Imipramine','Imipramine affects numerous neurotransmitter systems known to be involved in the etiology of depression, anxiety, attention-deficit hyperactivity disorder (ADHD), enuresis and numerous other mental and physical conditions. Imipramine is similar in structure to some muscle relaxants, and has a significant analgesic effect and, thus, is very useful in some pain conditions.', 0.9, 2.1, 'DOSE_DOUBLE', 'UNIT_MILLIGRAM')
INSERT INTO drug (name, description, minDose, maxDose, measure, unit) values ('Cipramil','Citalopram is an antidepressant drug of the selective serotonin reuptake inhibitor (SSRI) class. It has U.S. Food and Drug Administration approval to treat major depression, which it received in 1998, and is prescribed off-label for other conditions.', 0.6, 2.0, 'DOSE_DOUBLE', 'UNIT_MILLIGRAM')
INSERT INTO drug (name, description, minDose, maxDose, measure, unit) values ('Fluctin','Fluoxetine, also known by trade names Prozac and Sarafem, among others, is an antidepressant of the selective serotonin reuptake inhibitor (SSRI) class. It is used for the treatment of major depressive disorder, obsessive–compulsive disorder (OCD), bulimia nervosa, panic disorder and premenstrual dysphoric disorder. It may decrease the risk of suicide in those over the age of 65. It has also been used to treat premature ejaculation', 0.2, 1.75, 'DOSE_DOUBLE', 'UNIT_MILLIGRAM')
INSERT INTO drug (name, description, minDose, maxDose, measure, unit) values ('Seroxat','Paroxetine, also known by trade names including Paxil and Seroxat among others, is an antidepressant of the selective serotonin reuptake inhibitor (SSRI) class. It is used to treat major depressive disorder, obsessive-compulsive disorder, social anxiety disorder, panic disorder, posttraumatic stress disorder, generalized anxiety disorder and premenstrual dysphoric disorder. It has also been used in the treatment of hot flashes and night sweats associated with menopause.', 0.6, 0.9, 'DOSE_DOUBLE', 'UNIT_MILLILITER')
INSERT INTO drug (name, description, minDose, maxDose, measure, unit) values ('Zoloft','Treatment of panic disorder with sertraline results in a decrease of the number of panic attacks and an improved quality of life. In four double-blind studies sertraline was shown to be superior to placebo for the treatment of panic disorder. The response rate was independent of the dose. In addition to decreasing the frequency of panic attacks by about 80% (vs. 45% for placebo) and decreasing general anxiety, sertraline resulted in improvement of quality of life on most parameters.', 0.8, 3.2, 'DOSE_DOUBLE', 'UNIT_MILLILITER')


INSERT INTO patient_addiction (patient_id, addiction_id) values (1,1)
INSERT INTO patient_addiction (patient_id, addiction_id) values (2,2)
INSERT INTO patient_addiction (patient_id, addiction_id) values (3,3)
INSERT INTO patient_addiction (patient_id, addiction_id) values (4,4)
INSERT INTO patient_addiction (patient_id, addiction_id) values (5,5)
INSERT INTO patient_addiction (patient_id, addiction_id) values (6,6)
INSERT INTO patient_addiction (patient_id, addiction_id) values (7,7)
INSERT INTO patient_addiction (patient_id, addiction_id) values (8,8)
INSERT INTO patient_addiction (patient_id, addiction_id) values (9,9)
INSERT INTO patient_addiction (patient_id, addiction_id) values (10,10)
INSERT INTO patient_addiction (patient_id, addiction_id) values (11,1)
INSERT INTO patient_addiction (patient_id, addiction_id) values (12,2)
INSERT INTO patient_addiction (patient_id, addiction_id) values (13,3)
INSERT INTO patient_addiction (patient_id, addiction_id) values (14,4)
INSERT INTO patient_addiction (patient_id, addiction_id) values (15,5)
INSERT INTO patient_addiction (patient_id, addiction_id) values (16,6)
INSERT INTO patient_addiction (patient_id, addiction_id) values (17,7)
INSERT INTO patient_addiction (patient_id, addiction_id) values (18,8)
INSERT INTO patient_addiction (patient_id, addiction_id) values (19,9)
INSERT INTO patient_addiction (patient_id, addiction_id) values (20,10)
INSERT INTO patient_addiction (patient_id, addiction_id) values (21,1)
INSERT INTO patient_addiction (patient_id, addiction_id) values (22,2)
INSERT INTO patient_addiction (patient_id, addiction_id) values (23,3)
INSERT INTO patient_addiction (patient_id, addiction_id) values (24,4)
INSERT INTO patient_addiction (patient_id, addiction_id) values (25,5)
INSERT INTO patient_addiction (patient_id, addiction_id) values (26,6)
INSERT INTO patient_addiction (patient_id, addiction_id) values (27,7)
INSERT INTO patient_addiction (patient_id, addiction_id) values (28,8)
INSERT INTO patient_addiction (patient_id, addiction_id) values (29,9)
INSERT INTO patient_addiction (patient_id, addiction_id) values (30,10)


INSERT INTO patient_doctor (patient_id, doctor_id) values (1,1)
INSERT INTO patient_doctor (patient_id, doctor_id) values (2,2)
INSERT INTO patient_doctor (patient_id, doctor_id) values (3,3)
INSERT INTO patient_doctor (patient_id, doctor_id) values (4,4)
INSERT INTO patient_doctor (patient_id, doctor_id) values (5,5)
INSERT INTO patient_doctor (patient_id, doctor_id) values (6,6)
INSERT INTO patient_doctor (patient_id, doctor_id) values (7,7)
INSERT INTO patient_doctor (patient_id, doctor_id) values (8,1)
INSERT INTO patient_doctor (patient_id, doctor_id) values (9,2)
INSERT INTO patient_doctor (patient_id, doctor_id) values (10,3)
INSERT INTO patient_doctor (patient_id, doctor_id) values (11,4)
INSERT INTO patient_doctor (patient_id, doctor_id) values (12,5)
INSERT INTO patient_doctor (patient_id, doctor_id) values (13,6)
INSERT INTO patient_doctor (patient_id, doctor_id) values (14,7)
INSERT INTO patient_doctor (patient_id, doctor_id) values (15,1)
INSERT INTO patient_doctor (patient_id, doctor_id) values (16,2)
INSERT INTO patient_doctor (patient_id, doctor_id) values (17,3)
INSERT INTO patient_doctor (patient_id, doctor_id) values (18,4)
INSERT INTO patient_doctor (patient_id, doctor_id) values (19,5)
INSERT INTO patient_doctor (patient_id, doctor_id) values (20,6)
INSERT INTO patient_doctor (patient_id, doctor_id) values (21,7)
INSERT INTO patient_doctor (patient_id, doctor_id) values (22,1)
INSERT INTO patient_doctor (patient_id, doctor_id) values (23,2)
INSERT INTO patient_doctor (patient_id, doctor_id) values (24,3)
INSERT INTO patient_doctor (patient_id, doctor_id) values (25,4)
INSERT INTO patient_doctor (patient_id, doctor_id) values (26,5)
INSERT INTO patient_doctor (patient_id, doctor_id) values (27,6)
INSERT INTO patient_doctor (patient_id, doctor_id) values (28,7)
INSERT INTO patient_doctor (patient_id, doctor_id) values (29,1)
INSERT INTO patient_doctor (patient_id, doctor_id) values (30,2)
INSERT INTO patient_doctor (patient_id, doctor_id) values (31,3)
INSERT INTO patient_doctor (patient_id, doctor_id) values (32,4)
INSERT INTO patient_doctor (patient_id, doctor_id) values (33,5)
INSERT INTO patient_doctor (patient_id, doctor_id) values (34,6)
INSERT INTO patient_doctor (patient_id, doctor_id) values (35,7)
INSERT INTO patient_doctor (patient_id, doctor_id) values (36,1)
INSERT INTO patient_doctor (patient_id, doctor_id) values (37,2)
INSERT INTO patient_doctor (patient_id, doctor_id) values (38,3)
INSERT INTO patient_doctor (patient_id, doctor_id) values (39,4)
INSERT INTO patient_doctor (patient_id, doctor_id) values (40,5)
INSERT INTO patient_doctor (patient_id, doctor_id) values (41,6)
INSERT INTO patient_doctor (patient_id, doctor_id) values (42,7)
INSERT INTO patient_doctor (patient_id, doctor_id) values (43,1)
INSERT INTO patient_doctor (patient_id, doctor_id) values (44,2)
INSERT INTO patient_doctor (patient_id, doctor_id) values (45,3)
INSERT INTO patient_doctor (patient_id, doctor_id) values (46,4)
INSERT INTO patient_doctor (patient_id, doctor_id) values (47,5)
INSERT INTO patient_doctor (patient_id, doctor_id) values (48,6)
INSERT INTO patient_doctor (patient_id, doctor_id) values (49,7)
INSERT INTO patient_doctor (patient_id, doctor_id) values (50,1)
INSERT INTO patient_doctor (patient_id, doctor_id) values (51,2)
INSERT INTO patient_doctor (patient_id, doctor_id) values (52,3)
INSERT INTO patient_doctor (patient_id, doctor_id) values (53,4)
INSERT INTO patient_doctor (patient_id, doctor_id) values (54,5)
INSERT INTO patient_doctor (patient_id, doctor_id) values (55,6)
INSERT INTO patient_doctor (patient_id, doctor_id) values (56,7)


INSERT INTO appointment (name, description, start, end) VALUES ('test','test','2008-01-01 00:00:01','2008-01-01 00:00:01')


INSERT INTO notice(note, patient_id) values ('Test', 1)
INSERT INTO notice(note, patient_id) values ('Nochmals ein Test', 1)


INSERT INTO clinic_addiction(clinic_id, addiction_id) values (1,1)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (1,2)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (1,3)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (2,4)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (2,5)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (2,6)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (3,7)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (3,8)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (3,9)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (4,10)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (4,1)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (4,2)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (5,3)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (5,4)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (5,5)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (6,6)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (6,7)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (6,8)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (7,9)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (7,10)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (7,1)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (8,2)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (9,3)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (10,4)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (11,5)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (12,6)
INSERT INTO clinic_addiction(clinic_id, addiction_id) values (13,7)

