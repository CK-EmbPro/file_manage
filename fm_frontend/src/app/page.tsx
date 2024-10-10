"use client"
import { FileProps } from "@/types";
import Image from "next/image";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDownload } from "@fortawesome/free-solid-svg-icons";
import { ChangeEvent, FormEvent, useEffect, useState } from "react";
import { download, getFileNames, upload } from "@/services/serverActions";


export default function Home() {

  const [file_name, setfile_name] = useState<string | undefined>("")
  const [selectedFile, setselectedFile] = useState<File>()
  const [files, setfiles] = useState<string[]>([])
  const [uploadStatus, setuploadStatus] = useState<string>("")
  const [imageUrl, setimageUrl] = useState<string>("")

  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0]
    if (file) {
      setfile_name(file?.name)
      setselectedFile(file)
    }

  }

  const handleFileUpload = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    const formData = new FormData(e.currentTarget)
    const uploadMsg = await upload(formData)
    setselectedFile(undefined)
    setfile_name("")
    setuploadStatus(uploadMsg.data[0])
    alert(uploadMsg.data)
  }

  const handleDownload = async (fileName:string)=>{
    const blogUrl = await download(fileName)
    setimageUrl(blogUrl)
  }



  useEffect(() => {

    const getAllFiles = async () => {
      const data = await getFileNames()
      setfiles(data.data)
      console.log('data ', data.data.map(file=>file)
      );
    }
    getAllFiles()

  

  }, [uploadStatus])

  


  return (
    <div className="border border-red-500 flex flex-col gap-6 bg-headerBgColor h-full">
      <p className="text-[1.4em] text-green-700">Fm_manage</p>
      <div className="flex flex-col gap-6">
        <div className="flex flex-col gap-3">
          <p className="text-[1.2em]">Your files</p>
          <div className="flex px-5 flex-col bg-headerLinkBorderColor">
            {files.map((image, index) => (
              <div key={index} className="border border-green-500 flex gap-[70px] items-center ">
                <p className="text-[1.3em]">{image}</p>
                <a onClick={()=> handleDownload(image)} href={imageUrl} download={image}>
                  <FontAwesomeIcon icon={faDownload} className="w-6 h-6" />
                </a>
              </div>
            ))}
          </div>
        </div>
        <form encType="multipart/form-data" onSubmit={handleFileUpload} className=" flex gap-4 text-[1.3em] mx-auto">
          <input name="file" onChange={handleInputChange} id="upload" className="mx-auto" type="file" hidden />
          <label className="bg-textColor text-white p-1 px-3 rounded-[10px]" htmlFor="upload">select a file</label>
          <span>{file_name}</span>
          <button className="bg-textColor text-white p-1 px-3 rounded-[10px]">Upload</button>
        </form>
      </div>
    </div>
  );
}
