import React from 'react'
import { Base } from '../components/Base';
import EmployeeList from '../components/EmployeeList';
import userContext from '../context/userContext'
import Profile from './ProfileSelf';
import FunctionalityCard from '../components/FunctionalityCard';
import { Col, Row } from 'reactstrap';


const EmployeeDashboard = () => {

  
  return (

    <userContext.Consumer>{
      (object)=>(
        <>
        
        
        <Base >
          {
               (object.user.login) && (object.user.data) && (object.user.data.roles[0].id==501) && (
                <div className='container'>
                <Row>
                  <Col md={4} className="mb-4">
                    <FunctionalityCard nav="/employee/employeeList" title="View Employees" img={require('../images/Admin.jpg')} btn="View" msg="Coming together is a beginning, staying together is progress, and working together is success"/>
                  </Col>
                  <Col md={4} className="mb-4">
                    <FunctionalityCard nav="/employee/addEmployee" title="Add Employees" img={require('../images/Admin.jpg')} btn="Add" msg="Alone we can do so little, together we can do so much."/>
                  </Col>
                  <Col md={4} className="mb-4">
                    <FunctionalityCard nav="/employee/addDepartment" title="Add Department" img={require('../images/Admin.jpg')} btn="Add" msg="Divide the work and multiply the success."/>
                  </Col>
                  <Col md={4} className="mb-4">
                    <FunctionalityCard nav="/employee/leaveResponse" title="Leave Requests" img={require('../images/Admin.jpg')} btn="Approve/Reject" msg="Taking leaves from work is not a luxury, it's a necessity for your well-being."/>
                  </Col>
                </Row>

                
             
                </div>
                
                // <EmployeeList/>
              )
          }
          </Base>
         
          {
            (object.user.login) && (object.user.data) && (object.user.data.roles[0].id===502) && (
               
                <div className='container'>
                <Row>
                  <Col md={4} className="mb-4">
                  <FunctionalityCard nav="/employee/apply" title="Request Leave" img={require('../images/Leave.jpg')} btn="Apply" msg="Taking time off is the best way to find time for yourself."/>
                  </Col>
                  <Col md={4} className="mb-4">
                    <FunctionalityCard nav="/employee/view" title="View Leave status" img={require('../images/Leave.jpg')} btn="View" msg="Sometimes the most productive thing you can do is relax."/>
                  </Col>
                </Row>
                 
                </div>
            )
          }
          {console.log(object.user)}
        
        
          </> 
        
      )}
    </userContext.Consumer>

    
  )
}
export default EmployeeDashboard;