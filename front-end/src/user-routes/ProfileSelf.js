import React, { useContext, useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import { Card, CardBody, CardText, Col, Container, Row, Table } from 'reactstrap';
import { Base } from '../components/Base';
import userContext from '../context/userContext';
import { getDepartment } from '../services/department-service';

const Profile=()=> {
  
  const user=useContext(userContext)
  const [dep,setDep]=useState(null)
  
  useEffect(()=>{
    getDepartment(user.user.data.deptId).then(data=>{
      console.log("data")
      console.log(data.deptName)
      setDep(data)
    }).catch(error=>{
      console.log(error)
    })  
  },[])
  console.log("gg")
  console.log(user.user.data)
  const printDate=(numbers)=>{
    return new Date(numbers).toLocaleDateString()
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
            {/* {console.log(user.user.data.department.deptName)} */}
            <Table hover bordered={true} className='mt-5'>
              <tbody>
                <tr>
                  <td >
                    Employee Id
                  </td>
                  <td>
                    {user.user.data.id}
                  </td>
                </tr>
                <tr>
                  <td>
                    Employee Name
                  </td>
                  <td>
                    {user.user.data.firstName + " "+user.user.data.lastName}
                  </td>
                </tr>
                <tr>
                  <td >
                    Employee EMail
                  </td>
                  <td>
                    {user.user.data.emailId}
                  </td>
                </tr>
                <tr>
                  <td >
                    Joining Date
                  </td>
                  <td>
                    {printDate(user.user.data.joiningDate)}
                  </td>
                </tr>
                <tr>
                  <td>
                    Department Name
                  </td>
                  <td>
                    {(dep) && dep.deptName}
                  </td>
                </tr>
              </tbody>
            </Table>
          </CardBody>
        </Card>
      </Col>
    </Row>

    )
  }
  return (
<Base>
{user?view():''}
</Base>
    


    
  )
}

export default Profile;