import React, {useEffect, useContext, useState } from 'react'
import { useParams } from 'react-router-dom'
import { Base } from '../components/Base'
import userContext from '../context/userContext'
import { Form,Button, Card, CardBody, CardHeader, Col, Container, FormGroup, Input, Label, Row } from 'reactstrap'
import loadcategories from '../services/department-service'
import {  updateDetail,loadEmp } from '../services/user-service';

const UpdateDetail = () => {
    const {id} =useParams()
    //const object=useContext(userContext)
    const[departments,setDepartments]=useState([])
     //const [emp,setEmp]=useState(null)
    const [emp, setEmp] = useState({
        firstName:'',
        lastName:'',
        emailId:'',
        password:'',
        deptId:'',
    })

    useEffect(() => {
        loadEmp(id).then(data=>{
      
      setEmp(data)
      console.log(emp)
    }).catch(error=>{
      console.log(error)
    })

        loadcategories().then((data)=>{
            console.log(data)
            setDepartments(data)
        }).catch(error=>{
            console.log(error)
        })
    }, [])
        const handleChange=(event,property)=>{
        setEmp({...emp,[property]:event.target.value})
    }
    const submitForm=(e)=>{
        e.preventDefault();
        

        //validation
        if(emp.firstName.trim()===''){
            alert("FirstName is required")
            return;
        }
        if(emp.emailId.trim()===''){
            alert("EmailId is required")
            return;
        }
        if(emp.password.trim()===''){
            alert("password is required")
            return;
        }
        if(emp.deptId===''){
            alert("Assign Department")
            return;
        }

        console.log(emp)
        updateDetail(emp).then((resp)=>{
            console.log(resp)
            alert("Employee Updated")
        }).catch((error)=>{
            alert("Error")
           // console.log(error)
           // console.log("Error Log")
        })
    }
   const updateHtml=()=>{
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
                            <Input type='text' id='firstName' value={emp.firstName} onChange={(e)=>handleChange(e,'firstName')}/> 
                        </FormGroup>
                        <FormGroup>
                            <Label for="lastName">Enter Last Name</Label>
                            <Input type='text' id='lastName' value={emp.lastName} onChange={(e)=>handleChange(e,'lastName')}/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="emailId">Enter Email Id</Label>
                            <Input type='email' id='emailId' value={emp.emailId} onChange={(e)=>handleChange(e,'emailId')}/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="password">Enter Password</Label>
                            <Input type='password' id='password' value={emp.password} onChange={(e)=>handleChange(e,'password')}/>
                        </FormGroup>
                        <FormGroup>
                         <Label for="department">Department</Label>
                            <Input id="department" defaultValue={0} name="deptId" type="select" value={emp.deptId} onChange={(e)=>handleChange(e,'deptId')}>
                                <option disabled value={0}>-- Select Category --</option>
                            {
                                departments.map((department)=>(
                                    <>
                
                                    <option value={department.deptId} key={department.deptId }>{department.deptName}</option>
                                    </>
                             
                                ))
                            }
                            </Input> 
                        </FormGroup>
                        <Container className='text-center'>
                            <Button type='submit' color='dark'>Update</Button>
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
            {(emp) && updateHtml()}
        </Base>
  )
}

export default UpdateDetail