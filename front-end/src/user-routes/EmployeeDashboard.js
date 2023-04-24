import React from 'react'
import { Base } from '../components/Base';
import EmployeeList from '../components/EmployeeList';
import userContext from '../context/userContext'
import Profile from './ProfileSelf';

const EmployeeDashboard = () => {

  
  return (

    <userContext.Consumer>{
      (object)=>(
        <Base>
        <div className="container">
          {
               (object.user.login) && (object.user.data) && (object.user.data.roles[0].id==501) && (
                <EmployeeList/>
              )
          }
          {
            (object.user.login) && (object.user.data) && (object.user.data.roles[0].id===502) && (
               
                <>
                 <Profile/>
                </>
            )
          }
          {console.log(object.user)}
        
        </div>
            
        </Base>
      )}
    </userContext.Consumer>

    
  )
}
export default EmployeeDashboard;
