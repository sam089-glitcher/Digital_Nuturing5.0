import React from "react";
import CohortDetails from "./CohortDetails";

function App() {

  const cohorts = [
    {
      cohortCode: "INTADMDF10 - .NET FSD",
      startDate: "22-Feb-2022",
      status: "Scheduled",
      coach: "Aarthma",
      trainer: "Jojo Jose"
    },
    {
      cohortCode: "ADM21JF014 - Java FSD",
      startDate: "10-Sep-2021",
      status: "Ongoing",
      coach: "Apoorv",
      trainer: "Elisa Smith"
    },
    {
      cohortCode: "CDBJF21025 - Java FSD",
      startDate: "24-Dec-2021",
      status: "Ongoing",
      coach: "Aarthma",
      trainer: "John Doe"
    }
  ];

  return (
    <div>
      <h1>Cohorts Details</h1>

      {cohorts.map((cohort, index) => (
        <CohortDetails
          key={index}
          cohortCode={cohort.cohortCode}
          startDate={cohort.startDate}
          status={cohort.status}
          coach={cohort.coach}
          trainer={cohort.trainer}
        />
      ))}
    </div>
  );
}

export default App;