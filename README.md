# Eightfold Assignment
## Multi-Source Candidate Data Transformer

### Overview

This project implements a multi-source candidate data transformation pipeline.

It reads candidate information from multiple sources, normalizes the data, merges duplicate information into a canonical profile, records provenance, computes confidence, and generates a configurable JSON output.

---

## Features

- Recruiter CSV Parser (Structured Source)
- Resume TXT Parser (Unstructured Source)
- Candidate Merge Engine
- Email Normalization
- Phone Normalization (E.164 format)
- Skill Normalization
- Provenance Tracking
- Overall Confidence Calculation
- Runtime Configuration
- Projection Layer
- JSON Output
- CLI-based Execution

---

## Project Structure

```
input/
    recruiter.csv
    resume.txt

config/
    config.json

output/
    candidate.json

src/
    model/
    parser/
    merger/
    normalizer/
    projection/
    validation/
    writer/
```

---

## Technologies Used

- Java 17
- Maven
- Jackson
- OpenCSV
- Apache PDFBox
- Apache POI

---

## How to Run

1. Clone the repository.

2. Open in IntelliJ IDEA.

3. Reload Maven.

4. Place the sample files inside:

```
input/
```

5. Run:

```
Main.java
```

6. Output will be generated in:

```
output/candidate.json
```

---

## Design Decisions

- Recruiter CSV has the highest priority while merging.
- Emails are normalized to lowercase.
- Phone numbers are normalized to E.164 format.
- Duplicate skills are removed.
- Provenance is maintained for every merged field.
- Confidence score is computed based on profile completeness.
- Projection layer separates the internal model from the output schema.

---

## Assumptions

- One recruiter CSV record is processed per execution.
- Resume input is provided as TXT.
- Missing values are represented as null.
- Invalid values are ignored without stopping the pipeline.

---

## Edge Cases Handled

- Missing Email
- Missing Phone
- Duplicate Skills
- Duplicate Emails
- Duplicate Phones
- Invalid Phone Numbers
- Empty Resume

## Future Improvements

- PDF Resume Parsing
- DOCX Resume Parsing
- GitHub API Integration
- LinkedIn Profile Parsing
- Advanced Runtime Configuration