import {  useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";

function Register() {
  
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [passwordTouched, setPasswordTouched] = useState(false);

    const navigate = useNavigate();

    const isEmail = (email) => {
      return String(email)
        .toLowerCase()
        .match(
          /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
    };

    async function save(event) {
        event.preventDefault();

        if (username == "" || email == "" || password == "") {
          alert("Cannot have enpty fields");
          return;
        } 

        if (password !== confirmPassword) {
          alert("Passwords do not match");
          return;
        }
        if (!isEmail(email)) {
          alert("Please enter a valid email");
          return;
        }
        if (username.length != 9) {
          alert("Please enter a valid NRIC");
          return;
        }
        const passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=.*[a-zA-Z]).{8,}$/;
        if (!passwordPattern.test(password)) {
          alert("Password is not secure");
          return;
        }

        try {
          await axios.post("http://localhost:8080/api/auth/register", {
          username: username,
          email: email,
          password: password,
          }).then();
          alert("Registation Successfully");
          navigate('/login');
        } catch (error) {
          if (error.response && error.response.status === 400) {
            const errorMessage = error.response.data.message; 
            alert("Error Message:" + errorMessage);
          } else {
            console.error("Error:", error.message);
          }
        }
      }
  
      function validatePassword(password) {
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        return passwordRegex.test(password);
      }
    
      function handlePasswordInput(event) {
        const newPassword = event.target.value;
        setPassword(newPassword);
        setPasswordTouched(true);
      }

    return (
    <div>
    <div class="container mt-4" >
    <div class="card">
            <h1>Registation</h1>
    
    <form>
        <div class="form-group">
          <label>NRIC</label>
          <input type="text"  class="form-control" id="username" placeholder="Enter NRIC"
          
          value={username}
          onChange={(event) => {
            setUsername(event.target.value);
          }}
          />

        </div>

        <div class="form-group">
          <label>Email</label>
          <input type="email"  class="form-control" id="email" placeholder="Enter Email"
          
          value={email}
          onChange={(event) => {
            setEmail(event.target.value);
          }}
          
          />
 
        </div>

        <div className="form-group">
              <label>Password</label>
              <input
                type="password"
                className="form-control"
                id="password"
                placeholder="Enter Password"
                value={password}
                onChange={handlePasswordInput}
              />
              {passwordTouched && !validatePassword(password) && (
                <div className="text-danger">
                  Password must contain at least 8 characters, a lowercase and
                  uppercase letter, a number, and a special character (@$!%*?&)
                </div>
              )}
            </div>

        <div class="form-group">
            <label>Confirm Password</label>
            <input
              type="password" className="form-control" id="confirmPassword" placeholder="Confirm Password"

              value={confirmPassword}
              onChange={(event) => {
                setConfirmPassword(event.target.value);
              }}
            />
          </div>



        <button type="submit" class="btn btn-primary mt-4" onClick={save} >Register</button>
       
      </form>
      <p className="mt-3">Already created an account? <a href="/login">Log in here</a></p>
    </div>
    </div>
     </div>
    );
  }

  
  export default Register;