function NewGame(props) {

    const handleChange = (e) => {

        props.onChange(e.target.name, e.target.value);
    }

    return (
        <div className={"container-sm"}>
            <form className={""} onSubmit={props.submit}>
                <label className={"form-label"} htmlFor={"playerName"}>
                    Input your name
                </label>
                <input type={"text"} className={"form-control"} name={"playerName"} value={props.data.playerName} onChange={handleChange}/>
                <button className={"btn btn-lg btn-primary"} type={"submit"}>Start</button>
            </form>
        </div>
    );
}

export default NewGame;