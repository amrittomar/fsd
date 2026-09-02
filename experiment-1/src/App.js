import React, { useState } from "react";
import "./App.css";

const platforms = {
  Twitter: {
    icon: "🐦",
    color: "#1DA1F2",
    limit: 280,
    hashtags: true,
    media: true,
  },
  Instagram: {
    icon: "📸",
    color: "#E1306C",
    limit: 2200,
    hashtags: true,
    media: true,
  },
  LinkedIn: {
    icon: "💼",
    color: "#0077B5",
    limit: 3000,
    hashtags: false,
    media: true,
  },
};

function App() {
  const [selectedPlatforms, setSelectedPlatforms] = useState(["Twitter"]);
  const [post, setPost] = useState("");
  const [media, setMedia] = useState(null);
  const [message, setMessage] = useState("");

  const togglePlatform = (platform) => {
    setMessage("");

    if (selectedPlatforms.includes(platform)) {
      setSelectedPlatforms(
        selectedPlatforms.filter((p) => p !== platform)
      );
    } else {
      setSelectedPlatforms([...selectedPlatforms, platform]);
    }
  };

  const validate = (platform) => {
    const rule = platforms[platform];
    const errors = [];

    if (post.length > rule.limit)
      errors.push(`Character limit exceeded (${rule.limit})`);

    if (!rule.hashtags && post.includes("#"))
      errors.push("Hashtags are not allowed.");

    if (media && !rule.media)
      errors.push("Media upload not supported.");

    return errors;
  };

  const publishPost = () => {
    if (selectedPlatforms.length === 0) {
      setMessage("❌ Please select at least one platform.");
      return;
    }

    let valid = true;

    selectedPlatforms.forEach((platform) => {
      if (validate(platform).length !== 0) valid = false;
    });

    if (valid)
      setMessage("✅ Post Published Successfully!");
    else
      setMessage("❌ Please resolve all validation errors.");
  };

  const clearPost = () => {
    setPost("");
    setMedia(null);
    setMessage("");
  };

  return (
    <div className="container">

      <div className="card">

        <h1>🌐 Social Media Post Composer</h1>

        <p className="subtitle">
          Compose once and validate for multiple platforms.
        </p>

        <h2>Select Platforms</h2>

        <div className="platformContainer">

          {Object.keys(platforms).map((platform) => (

            <button
              key={platform}
              className={
                selectedPlatforms.includes(platform)
                  ? "platform active"
                  : "platform"
              }
              onClick={() => togglePlatform(platform)}
            >
              {platforms[platform].icon} {platform}
            </button>

          ))}

        </div>

        <textarea
          placeholder="What's on your mind?"
          value={post}
          onChange={(e) => setPost(e.target.value)}
        />

        <div className="toolbar">

          <label className="uploadButton">

            📎 Upload Media

            <input
              type="file"
              hidden
              onChange={(e) =>
                setMedia(
                  e.target.files.length
                    ? e.target.files[0].name
                    : null
                )
              }
            />

          </label>

          <span>
            {media ? media : "No media selected"}
          </span>

        </div>

        <h2>Validation</h2>

        {selectedPlatforms.map((platform) => {

          const errors = validate(platform);

          return (

            <div className="validationCard" key={platform}>

              <div className="heading">

                <span>
                  {platforms[platform].icon} {platform}
                </span>

                <span>
                  {post.length}/{platforms[platform].limit}
                </span>

              </div>

              {errors.length === 0 ? (
                <p className="success">
                  ✅ Ready to Publish
                </p>
              ) : (
                errors.map((error, index) => (
                  <p className="error" key={index}>
                    ❌ {error}
                  </p>
                ))
              )}

            </div>

          );

        })}

        <div className="buttons">

          <button className="clearBtn" onClick={clearPost}>
            🗑 Clear
          </button>

          <button className="publishBtn" onClick={publishPost}>
            🚀 Publish Post
          </button>

        </div>

        {message && (
          <div className="message">
            {message}
          </div>
        )}

      </div>
    </div>
  );
}

export default App;