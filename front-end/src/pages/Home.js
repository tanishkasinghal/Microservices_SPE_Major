import React from 'react'
import { Base } from '../components/Base';
import EmployeeList from '../components/EmployeeList';
import userContext from '../context/userContext'

const Home = () => {

  
  return (

    <userContext.Consumer>{
      (object)=>(
        <Base>
        <div className="container">
          {
            // (object.user.login) && (object.user.employees.roles[0].id==501) && (
            //   <EmployeeList/>
            // )
           // console.log(object)
            // (user) && (!user.roles[0]==501) && (
            //   <h1>Welcome {user.firstName}</h1>
            // )
          }
        <h1>Home page</h1>
        </div>
            
        </Base>
      )}
    </userContext.Consumer>

    
  )
}
export default Home;
