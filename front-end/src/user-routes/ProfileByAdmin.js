import React, { useContext, useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Card, CardBody, CardText, Col, Container, Row, Table } from 'reactstrap';
import { Base } from '../components/Base';
import userContext from '../context/userContext';
import { deleteEmp, loadEmp } from '../services/user-service';

const ProfileByAdmin=()=> {

  const user=useContext(userContext)
  const navigate=useNavigate();
  const [emp,setEmp]=useState(null)
  useEffect(()=>{
    console.log(user)
    loadEmp(id).then(data=>{
      console.log(data)
      setEmp(data)
    }).catch(error=>{
      console.log(error)
    })
  },[])
  const printDate=(numbers)=>{
    return new Date(numbers).toLocaleDateString()
  }
  const {id}=useParams()
  function deleteEmployee(data){
    deleteEmp(data).then(res=>{
      console.log(res)
      alert("emp deleted")
      navigate("/employee/dashboard")
    }).catch(error=>{
      console.log(error)
    })
  }

  const view=()=>{
    return(
      <Row>
      <Col md={{size:8,offset:2}}>
        <Card>
          <CardBody>
            <h3 className='text-uppercase'>User Information</h3>
            <Container className='text-center'>
              <img style={{maxWidth:'200px',maxHeight:'200px'}} src={require('../images/profile_picture.png')} alt='user profile picture' className='img-fluid'/>
            </Container>
            <Table hover bordered={true} className='mt-5'>
              <tbody>
                <tr>
                  <td >
                    Employee Id
                  </td>
                  <td>
                    {emp.id}
                  </td>
                </tr>
                <tr>
                  <td>
                    Employee Name
                  </td>
                  <td>
                    {emp.firstName + " "+emp.lastName}
                  </td>
                </tr>
                <tr>
                  <td >
                    Employee Email
                  </td>
                  <td>
                    {emp.emailId}
                  </td>
                </tr>
                <tr>
                  <td >
                    Joining Date
                  </td>
                  <td>
                    {printDate(emp.joiningDate)}
                  </td>
                </tr>
                <tr>
                  <td>
                    Department Name
                  </td>
                  <td>
                    {emp.department.deptName}
                  </td>
                </tr>
              </tbody>
            </Table>
            {
              (emp.roles[0].id==502) && (<>
               <Button color='danger' className='ms-2' onClick={()=>deleteEmployee(emp)}>Delete this Employee</Button>
               <Button tag={Link} to={`/employee/update-detail/${emp.id}`} color='warning' className='ms-2'>Update Details</Button>
              </>
               
              )
            }

            
          </CardBody>
        </Card>
      </Col>
    </Row>
    )
  }
  return (
    <Base>
    
    {emp?view():'Loading User Data...'}

    {/* <Container className='mt-4'>
 
      <Link to="/">Home</Link>
      <Row>
        <Col md={{
          size:12
        }}>
        <Card className='mt-3'>
          {
              (emp) && (
                <CardBody >
                  <CardText> {user.user.data.firstName}</CardText>
                    <CardText> Employee - <b>{emp.firstname}</b> joined <b>{printDate(emp.joiningDate)}</b></CardText>
                    <CardText>Department - {emp.department.deptName}</CardText>
                  </CardBody>
               )
          }
         
        </Card>
        </Col>
      </Row>
    </Container> */}
    </Base>
    
  )
}

export default ProfileByAdmin;