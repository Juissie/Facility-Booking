import {  useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";


function Login() {
   
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();


    async function login(event) {
        event.preventDefault();
        try {
          await axios.post("http://localhost:8080/api/auth/login", {
            username: username,
            password: password,
            }).then((res) => 
            {
             console.log(res.data);
             alert("Login Successful");
             navigate("/home");
            });
          // }, fail => {
          //  console.error(fail); // Error!
          //  alert("Failed");
          // });
        }
         catch (err) {
          alert("Wrong username or password");
        }
      }

    return (
       <div>
            <div class="container">
            <div class="row">
                <h2>Login</h2>
             <hr/>
             </div>

             <div class="row">
             <div class="col-sm-6">
 
            <form>
        <div class="form-group">
          <label>NRIC</label>
          <input type="username"  class="form-control" id="username" placeholder="Enter NRIC"
          
          value={username}
          onChange={(event) => {
            setUsername(event.target.value);
          }}
          
          />

        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password"  class="form-control" id="password" placeholder="Enter Password"
            
            value={password}
            onChange={(event) => {
              setPassword(event.target.value);
            }}
            
            />
          </div>
                  <button type="submit" class="btn btn-primary" onClick={login} >Login</button>
              </form>

            <p className="mt-3">Don't have an account yet? <a href="/register">Create one here</a></p>
          </div>

        </div>
      </div>

     </div>
    );
  }
  
  export default Login;