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


    async function save(event) {
        event.preventDefault();

        if (password !== confirmPassword) {
          alert("Passwords do not match");
          return;
        }

        try {
          await axios.post("http://localhost:8080/register", {
          username: username,
          email: email,
          password: password,
          });
          alert("Registation Successfully");
          navigate('/login');
        } catch (err) {
          alert(err);
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
          <label>Username</label>
          <input type="text"  class="form-control" id="username" placeholder="Enter Username"
          
          value={username}
          onChange={(event) => {
            setUsername(event.target.value);
          }}
          />

        </div>

        <div class="form-group">
          <label>email</label>
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
    </div>
    </div>
     </div>
    );
  }
  
  export default Register;