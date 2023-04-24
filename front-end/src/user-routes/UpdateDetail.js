import React, { useContext } from 'react'
import { useParams } from 'react-router-dom'
import { Base } from '../components/Base'
import userContext from '../context/userContext'

const UpdateDetail = () => {
    const {id} =useParams()
    const object=useContext(userContext)
  return (
        <Base>
            {id}
        </Base>
  )
}

export default UpdateDetail