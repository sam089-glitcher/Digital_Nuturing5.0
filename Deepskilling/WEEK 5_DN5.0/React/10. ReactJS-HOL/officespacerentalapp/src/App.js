import "./App.css";

function App() {

  const heading = "Office Space";

  const officeImage = "/office.jpg";

  const officeList = [

    {
      Name: "DBS",
      Rent: 50000,
      Address: "Chennai"
    },

    {
      Name: "TCS",
      Rent: 70000,
      Address: "Bangalore"
    },

    {
      Name: "Infosys",
      Rent: 55000,
      Address: "Hyderabad"
    },

    {
      Name: "Wipro",
      Rent: 85000,
      Address: "Pune"
    }

  ];

  return (

    <div className="container">

      <h1>{heading}, at Affordable Range</h1>

      <img
        src={officeImage}
        alt="Office Space"
        width="300"
      />

      <br />
      <br />

      {

        officeList.map((office, index) => (

          <div key={index} className="card">

            <h2>Name: {office.Name}</h2>

            <h3
              style={{
                color:
                  office.Rent <= 60000
                    ? "red"
                    : "green"
              }}
            >
              Rent: Rs. {office.Rent}
            </h3>

            <h3>
              Address: {office.Address}
            </h3>

            <hr />

          </div>

        ))

      }

    </div>

  );

}

export default App;