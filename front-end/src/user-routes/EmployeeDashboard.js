import React from 'react'
import { Base } from '../components/Base';
import EmployeeList from '../components/EmployeeList';
import userContext from '../context/userContext'
import Profile from './ProfileSelf';

const EmployeeDashboard = () => {

  
  return (

    <userContext.Consumer>{
      (object)=>(
<<<<<<< HEAD
        <>
        
        
        <div >
=======
        <Base>
        <div className="container">
>>>>>>> 06c6db59a885ec203916cddb2e61d4d33c4bca4e
          {
               (object.user.login) && (object.user.data) && (object.user.data.roles[0].id==501) && (
                <EmployeeList/>
              )
          }
<<<<<<< HEAD
          </div>
         
=======
>>>>>>> 06c6db59a885ec203916cddb2e61d4d33c4bca4e
          {
            (object.user.login) && (object.user.data) && (object.user.data.roles[0].id===502) && (
               
                <>
                 <Profile/>
                </>
            )
          }
          {console.log(object.user)}
        
<<<<<<< HEAD
        
          </> 
        
=======
        </div>
            
        </Base>
>>>>>>> 06c6db59a885ec203916cddb2e61d4d33c4bca4e
      )}
    </userContext.Consumer>

    
  )
}
export default EmployeeDashboard;
