# mimic-openmrs-cdss

Our clinical decision support system (CDSS) is an implementation of a Learning Health System in OpenMRS. It uses
data from MIMIC-IV v1.4 hosp and icu modules imported into OpenMRS to validate the CDSS implementation.

### Novelty
Traditionally, CDSS systems in EHR systems are hard-coded as a rule-based system based on prior knowledge
published as Clinical Practice Guidelines. More recent implementations with dynamic machine learning (ML)
models use a pre-selected cohort or randomized train-test sets. Instead, we generate a cohort of similar patients
in the EHR database based on characteristics like `age-group`, `sex`, `primary diagnosis` etc. and use clinical
observations of this cohort to train a model and make a risk-factor classification based on the clinician's query.
The CDSS provides a probability score for the likelihood of the patient having the disease.

### Features
Legacy UI patient dashboard tab that can do the following:
- Automatically create cohort on patient demographics and primary diagnosis.
- Clinician can select concepts that should be used as features to train a model. Default feature selection is done.
- Clinician can select a diagnosis and likelihood of the patient with the diagnosis is reported.

### Folder structure
You will have to manually download the MIMIC-IV v1.4 csv.gz files prior to running the notebooks in the `/data` folder
 - `/data` (notebooks to import data from MIMIC-IV csv to OpenMRS MySQL database)
 - `/cdss` (notebooks implementing an iPython dashboard)
 - `/model_evaluation` (notebooks and files containing the SQL queries to extract 10k patient's data from MySQL and ML model evaluation codes)
 - `/openmrs-module-cdss` (OpenMRS module that integrates iPython dashboard into Legacy UI patient dashboard)

### How to run
> **Warning**
>: Ensure that you have at least 128 GB of RAM, Quad-core CPU, 200+ GB of disk space to be able to import the data 
> and run the cdss dashboard appropriately.

> Install Jupyter Notebook Params extension: https://github.com/manics/jupyter-notebookparams <br/>
> Install Jupyter Dashboard extension: https://github.com/jupyter-attic/dashboards (TODO: upgrade to Voila)

This assumes that you are doing a clean installation of OpenMRS without any test data. Additional deployment
considerations are ignored. The `/data` notebooks will overwrite any existing data. So, use carefully.
Steps:
1. Setup OpenMRS on MySQL using the usual OpenMRS Installer, without demo data.
2. Ensure that OpenMRS can start without issues after the installation is complete. Install the legacy-ui module.
3. Copy the two CSVs along with the notebooks from `/data` folder into your `OPENMRS_HOME` folder.
4. Install jupyter using `pip install notebook` and start it using `jupyter notebook`.
5. Open and run the notebook in order of the file names, e.g., `1_patient_details`, `2_encounter_table`, etc.
6. Once all the notebooks are run, get into OpenMRS in the browser and perform indexing.
   * Login &rarr; Administration &rarr; Maintenance &rarr; Search Index &rarr; Press the `Rebuild Search Index` button.
7. Find/Create Patient and look for `mimic` and you should get some search, if everything worked correctly.
8. Build the omrs-cdss module:
   * `cd openmrs-module-cdss`
   * `mvn clean install`
   * Install the module `omrs-cdss-1.0.0.omod` from `/openmrs-module-cdss/omod/target` using Administration &rarr; 
   Manage Modules
9. Go to Patient dashboard after searching for patient and play along!!

### How to run the CDSS Dashboard:

1.	Log in Credentials in the screen that looks like below:
 - 	Username: demo_user
 - 	Password: User@123
![image](https://github.com/iupui-soic/mimic-openmrs-cdss/assets/115053509/a54556d2-7062-4690-8d1e-71041b4e07d6)
![image](https://github.com/iupui-soic/mimic-openmrs-cdss/assets/115053509/18b6bd0c-aa51-4767-bb07-f155e9ecbccc)

2.	After logging in you will see a page like this.
3.	Go the tab that says “Find/Create Patient”.
![image](https://github.com/iupui-soic/mimic-openmrs-cdss/assets/115053509/9e0552f1-f859-49f3-a719-c1681c38aa29)

4.	Search for a mimic patient/any patient in the “Patient Identifier or Patient Name”.
![image](https://github.com/iupui-soic/mimic-openmrs-cdss/assets/115053509/8ba804bd-232e-4acf-9155-65e005349dc9)

5.	After searching for the patient, you will see a window which appears like the picture below, and you can select the specific patient for whom you want to run the ML analysis by clicking on it.
![image](https://github.com/iupui-soic/mimic-openmrs-cdss/assets/115053509/50ec01b9-bfc3-47f5-b392-ba8b94d91266)

6. Once you click on it you will see a page that looks like this, then you have click on the “Clinical Decision support System” tab.
![image](https://github.com/iupui-soic/mimic-openmrs-cdss/assets/115053509/ffb80bac-73ff-401c-b629-5c703feff6ad)


### Acknowledgements
The project started as `@ReginaMerine`'s INFO-B691 Capstone project 
