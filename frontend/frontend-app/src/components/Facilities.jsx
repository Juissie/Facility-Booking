import React, { useState, useEffect } from "react";
import axios from "axios";
import MyNavbar from './NavbarComp';
import 'bootstrap/dist/css/bootstrap.min.css';

function FacilityList() {
  const [facilities, setFacilities] = useState([]);

  useEffect(() => {
    // Fetch facilities data from your API endpoint
    axios.get("http://localhost:8080/api/facilities")
      .then((response) => {
        setFacilities(response.data);
      })
      .catch((error) => {
        console.error("Error fetching facilities:", error);
      });
  }, []);

  return (
    <div>
        <MyNavbar /> 
      <h1>Facility List</h1>
      <ul>
        {facilities.map((facility) => (
          <li key={facility.id}>
            <strong>Facility Type:</strong> {facility.facilityType}
            <br />
            <strong>Description:</strong> {facility.description}
            <br />
            <ul>
              {facility.timeSlots.map((timeSlot, index) => (
                <li key={index}>{timeSlot}</li>
              ))}
            </ul>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default FacilityList;
