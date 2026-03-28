const JSZip = require('jszip');
const fs = require('fs');
const path = require('path');

const zip = new JSZip();

const resourcesDir = 'src/main/resources';
const files = fs.readdirSync(resourcesDir);
files.forEach(file => {
    const filePath = path.join(resourcesDir, file);
    const data = fs.readFileSync(filePath);
    zip.file(file, data);
    console.log('Added: ' + file);
});

zip.generateNodeStream({ type: 'nodebuffer', streamFiles: true })
    .pipe(fs.createWriteStream('src/main/resources/assets.epk'))
    .on('finish', () => {
        console.log('assets.epk created successfully!');
    });