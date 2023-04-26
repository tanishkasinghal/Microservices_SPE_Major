import React, { useEffect, useContext } from 'react'
import { useParams } from 'react-router-dom'
import { Base } from '../components/Base'
import userContext from '../context/userContext'

import {  loadEmp } from '../services/user-service';

const UpdateDetail = () => {
      const [emp,setEmp]=useState(null)
    const {id} =useParams()
    const object=useContext(userContext)
    useEffect(()=>{
    loadEmp(id).then(data=>{
      setEmp(data)
    }).catch(error=>{
      console.log(error)
    })
  },[])

    const UpdateHtml=()=>{
        return(
            <Container>
            <Row className='mt-4'>

                {/* {JSON.stringify(data)} */}
                <Col sm={{size:6,offset:3}}>
                <Card>
                <CardHeader>
                    <h3>Update Details</h3>
                </CardHeader>
                <CardBody>
                    {/* {JSON.stringify(data)} */}   
                    <Form onSubmit={submitForm}>
                        <FormGroup>
                            <Label for="firstName">Enter First Name</Label>
                            <Input type='text' id='firstName' value={data.firstName} onChange={(e)=>handleChange(e,'firstName')}/> 
                        </FormGroup>
                        <FormGroup>
                            <Label for="lastName">Enter Last Name</Label>
                            <Input type='text' id='lastName' value={data.lastName} onChange={(e)=>handleChange(e,'lastName')}/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="emailId">Enter Email Id</Label>
                            <Input type='email' id='emailId' value={data.emailId} onChange={(e)=>handleChange(e,'emailId')}/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="password">Enter Password</Label>
                            <Input type='password' id='password' value={data.password} onChange={(e)=>handleChange(e,'password')}/>
                        </FormGroup>
                        <FormGroup>
                         <Label for="department">Department</Label>
                            <Input id="department" defaultValue={0} name="deptId" type="select" onChange={(e)=>handleChange(e,'deptId')}>
                                <option disabled value={0}>-- Select Category --</option>
                            {
                                departments.map((department)=>(
                                    <>
                
                                    <option value={department.deptId} key={department.deptId }>{department.deptName}</option>
                                    </>
                             
                                ))
                            }
                            </Input> 
                         {/*<Label for="manager">Manager</Label>
                            <Input id="manager" defaultValue={0} name="manager.id" type="select" onChange={(e)=>handleChange(e,'manager.id')}>
                                <option disabled value={0}>-- Select Category --</option>
                            {
                                departments.map((department)=>(
                                    <option value={department.id} key={department.id }>{department.deptName}</option>
                                ))
                            }
                            </Input>*/}
                        </FormGroup>
                        <Container className='text-center'>
                            <Button type='submit' color='dark'>Register</Button>
                            <Button color='secondary' className='ms-2' type='reset' onClick={resetData}>Reset</Button>
                        </Container>
                    </Form>
                </CardBody>
            </Card>
                </Col>
            </Row>
        </Container>
            )
    }
  return (
        <Base>
            {id}
        </Base>
  )
}

export default UpdateDetail