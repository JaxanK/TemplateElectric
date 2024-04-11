::Remove directory and recreate it
set "dstDir=I:\My Drive\Designer\Program\TemplateElectric\bin"

::Copy specific from this directory to destination
xcopy   ".\target\TemplateElectric-*-standalone.jar" "%dstDir%"  /y



