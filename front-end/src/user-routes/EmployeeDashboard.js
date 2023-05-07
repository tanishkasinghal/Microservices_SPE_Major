import React from 'react'
import { Base } from '../components/Base';
import EmployeeList from '../components/EmployeeList';
import userContext from '../context/userContext'
import Profile from './ProfileSelf';

const EmployeeDashboard = () => {

  
  return (

    <userContext.Consumer>{
      (object)=>(
        <>
        
        
        <div >
          {
               (object.user.login) && (object.user.data) && (object.user.data.roles[0].id==501) && (
                <EmployeeList/>
              )
          }
          </div>
         
          {
            (object.user.login) && (object.user.data) && (object.user.data.roles[0].id===502) && (
               
                <>
                 <Profile/>
                </>
            )
          }
          {console.log(object.user)}
        
        
          </> 
        
      )}
    </userContext.Consumer>

    
  )
}
export default EmployeeDashboard;
