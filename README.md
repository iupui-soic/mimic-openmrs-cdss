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
 - `/openmrs-module-cdss` (OpenMRS module that integrates iPython dashboard into Legacy UI patient dashboard)

### How to run
> **Warning**
>: Ensure that you have at least 128 GB of RAM, Quad-core CPU, 200+ GB of disk space to be able to import the data 
> and run the cdss dashboard appropriately.

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

### Screenshots
TBD

### Acknowledgements
The project started as `@ReginaMerine`'s INFO-B691 Capstone project 
