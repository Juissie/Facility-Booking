import React, { useState, useEffect } from "react";
import axios from "axios";
import MyNavbar from './NavbarComp';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';
import Modal from 'react-modal';

function FacilityList() {
  const [facilities, setFacilities] = useState([]);
  const [selectedFacility, setSelectedFacility] = useState(null);
  const [modalIsOpen, setModalIsOpen] = useState(false);

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

  // Function to open the modal and set the selected facility
  const openModal = (facility) => {
    setSelectedFacility(facility);
    setModalIsOpen(true);
  };

  // Function to close the modal
  const closeModal = () => {
    setSelectedFacility(null);
    setModalIsOpen(false);
  };

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
            <button onClick={() => openModal(facility)} className="view-timeslots-button">
              View Timeslots
            </button>
          </li>
        ))}
      </ul>
      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Timeslots Modal"
      >
        {selectedFacility && (
          <div>
            <h2>Timeslots for Facility: {selectedFacility.facilityType}</h2>
            {/* Fetch and display timeslots for the selected facility here */}
            <button onClick={closeModal}>Close</button>
          </div>
        )}
      </Modal>
    </div>
  );
}

export default FacilityList;