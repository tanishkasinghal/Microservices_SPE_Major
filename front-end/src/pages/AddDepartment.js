import React, {  useState } from 'react';
import { Button, Card, CardBody, CardHeader, Col, Container, Form, FormGroup, Input, Label, Row } from 'reactstrap';
import { Base } from '../components/Base';
import { addDepartment } from '../services/department-service';

const AddDepartment = () => {

    const [data, setData] = useState({
        deptName:''
    })

    const [error, setError] = useState({
        error:{},
        isError:false
    })

    const handleChange=(event,property)=>{
        setData({...data,[property]:event.target.value})
    }

    const resetData=()=>{
        setData({
            deptName:''
        })
    }

    const submitForm=(e)=>{
        e.preventDefault();
        

        //validation
        if(data.deptName.trim()===''){
            alert("deptName is required")
            return;
        }
       
       // console.log(data)
        addDepartment(data).then((resp)=>{
            alert("Department Registered")
            setData({
                deptName:''
            })
  
        }).catch((error)=>{
            alert("Error")
          
        })
    }
  return (
    <Base>
        <Container>
            <Row className='mt-4'>

                {/* {JSON.stringify(data)} */}
                <Col sm={{size:6,offset:3}}>
                <Card>
                <CardHeader>
                    <h3>Register - Add Department Info</h3>
                </CardHeader>
                <CardBody>
                    {/* {JSON.stringify(data)} */}   
                    <Form onSubmit={submitForm}>
                        <FormGroup>
                            <Label for="deptName">Department Name</Label>
                            <Input type='text' id='deptName' value={data.deptName} onChange={(e)=>handleChange(e,'deptName')}/> 
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
    </Base>
  )
}

export default AddDepartment;
