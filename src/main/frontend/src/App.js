import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import {useDropzone} from 'react-dropzone'
import "./App.css";

function MyDropzone({id}) {
  const onDrop = useCallback((acceptedFiles) => {
    // Do something with the files
    const file = acceptedFiles[0];
    const formData = new FormData();
    formData.append("file", file);
    console.log({id});
    axios.post(`http://localhost:8080/amazon/${id}/image/upload`,
      formData
    )
    .then(() => {
      console.log("File uploaded successfully");
    })
    .catch((error) => {
      console.log(error);
    })

  }, []);
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {isDragActive ? (
        <p>Drop the files here ...</p>
      ) : (
        <p>Drag 'n' drop some files here, or click to select files</p>
      )}
    </div>
  );
}

const UserProfiles = () => {
  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUsers = () => {
    axios
      .get("http://localhost:8080/amazon/")
      .then((res) => {
        setUserProfiles(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  useEffect(() => {
    fetchUsers();
  }, []);
  return userProfiles.map((profile, index) => {
    return (
      <div key={index}>
        <h1>{profile.name}</h1>
        <p>{profile.id}</p>
        <MyDropzone {...profile}/>
      </div>
    );
  });
};

function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
