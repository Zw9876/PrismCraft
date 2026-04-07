const JSZip = require('jszip');
const fs = require('fs');
const path = require('path');

const zip = new JSZip();

// Recursively add files from a directory
function addFilesFromDir(dir, baseDir) {
    const files = fs.readdirSync(dir);
    files.forEach(file => {
        const filePath = path.join(dir, file);
        const stat = fs.statSync(filePath);
        if (stat.isDirectory()) {
            addFilesFromDir(filePath, baseDir);
        } else {
            const zipPath = path.relative(baseDir, filePath).replace(/\\/g, '/');
            const data = fs.readFileSync(filePath);
            zip.file(zipPath, data);
            console.log('Added: ' + zipPath);
        }
    });
}

// Add assets from extracted minecraft assets if they exist
const mcAssetsDir = 'build/mc-assets';
if (fs.existsSync(mcAssetsDir)) {
    console.log('Adding Minecraft assets from ' + mcAssetsDir);
    addFilesFromDir(mcAssetsDir, mcAssetsDir);
} else {
    console.log('No Minecraft assets found, using placeholder assets only');
}

// Always add our custom resources (overrides MC assets if same name)
const resourcesDir = 'src/main/resources';
const files = fs.readdirSync(resourcesDir);
files.forEach(file => {
    const filePath = path.join(resourcesDir, file);
    const stat = fs.statSync(filePath);
    // Skip subdirectories and the epk itself
    if (!stat.isDirectory() && file !== 'assets.epk' && file !== 'index.html') {
        const data = fs.readFileSync(filePath);
        zip.file(file, data);
        console.log('Added custom: ' + file);
    }
});

zip.generateNodeStream({ type: 'nodebuffer', streamFiles: true, compression: 'DEFLATE' })
    .pipe(fs.createWriteStream('src/main/resources/assets.epk'))
    .on('finish', () => {
        console.log('assets.epk created successfully!');
    });